package com.maestrano.sso;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import com.maestrano.Maestrano;
import com.maestrano.SsoService;
import com.maestrano.exception.MnoException;
import com.maestrano.helpers.MnoDateHelper;

public class MnoUser {

	public final String ssoSession;
	public final Date ssoSessionRecheck;
	public final String groupUid;
	public final String groupRole;
	public final String uid;
	public final String virtualUid;
	public final String email;
	public final String virtualEmail;
	public final String firstName;
	public final String lastName;
	public final String country;
	public final String companyName;

	/**
	 * Constructor
	 * 
	 * @param samlResponse
	 *            a SAML Response from Maestrano IDP
	 * @throws ParseException
	 */
	public MnoUser(com.maestrano.saml.Response samlResponse) throws ParseException {
		Map<String, String> att = samlResponse.getAttributes();

		this.ssoSession = att.get("mno_session");
		this.ssoSessionRecheck = MnoDateHelper.fromIso8601(att.get("mno_session_recheck"));
		this.groupUid = att.get("group_uid");
		this.groupRole = att.get("group_role");
		this.uid = att.get("uid");
		this.virtualUid = att.get("virtual_uid");
		this.email = att.get("email");
		this.virtualEmail = att.get("virtual_email");
		this.firstName = att.get("name");
		this.lastName = att.get("surname");
		this.country = att.get("country");
		this.companyName = att.get("company_name");
	}

	private static SsoService ssoService() {
		return Maestrano.getDefault().ssoService();
	}

	/**
	 * Return the real UID if Maestrano Sso Creation Mode is set to "real" and the Virtual UID otherwise ("virtual" mode)
	 * 
	 * @return String uid to use in application
	 * @throws MnoException
	 */
	public String toUid() {
		if (ssoService().getCreationMode().equals("real")) {
			return this.uid;
		} else {
			return this.virtualUid;
		}
	}

	/**
	 * Return the real email if Maestrano Sso Creation Mode is set to "real" and the Virtual email otherwise ("virtual" mode)
	 * 
	 * @return
	 * @throws MnoException
	 */
	public String toEmail() {
		if (ssoService().getCreationMode().equals("real")) {
			return this.email;
		} else {
			return this.virtualEmail;
		}
	}

	/**
	 * Return the current user session token
	 * 
	 * @return String session token
	 */
	public String getSsoSession() {
		return ssoSession;
	}

	/**
	 * Return when the user session should be remotely checked
	 * 
	 * @return Date session check time
	 */
	public Date getSsoSessionRecheck() {
		return ssoSessionRecheck;
	}

	/**
	 * Return the user group UID
	 * 
	 * @return String group UID
	 */
	public String getGroupUid() {
		return groupUid;
	}

	/**
	 * Return the user role in the group Roles are: 'Member', 'Power User', 'Admin', 'Super Admin'
	 * 
	 * @return String user role in group
	 */
	public String getGroupRole() {
		return groupRole;
	}

	/**
	 * The Maestrano user UID
	 * 
	 * @return String user UID
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * The user virtual UID which is truly unique across users and groups
	 * 
	 * @return String user virtual uid
	 */
	public String getVirtualUid() {
		return virtualUid;
	}

	/**
	 * The actual user email
	 * 
	 * @return String user email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Virtual email that can be used instead of regular email fields This email is unique across users and groups All emails sent to this email address are redirected to the real user email
	 * 
	 * @return String virtual email
	 */
	public String getVirtualEmail() {
		return virtualEmail;
	}

	/**
	 * User first name
	 * 
	 * @return String user first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * User last name
	 * 
	 * @return String user last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * ALPHA2 code of user country
	 * 
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Company name entered by the user Can be empty
	 * 
	 * @return String company name
	 */
	public String getCompanyName() {
		return companyName;
	}
}

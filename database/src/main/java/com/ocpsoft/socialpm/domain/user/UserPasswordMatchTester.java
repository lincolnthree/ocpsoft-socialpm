/**
 * This file is part of SocialPM: Agile Project Management Tools (SocialPM) 
 *
 * Copyright (c)2010 Lincoln Baxter, III <lincoln@ocpsoft.com> (OcpSoft)
 * 
 * If you are developing and distributing open source applications under 
 * the GPL Licence, then you are free to use SocialPM under the GPL 
 * License:
 *
 * SocialPM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SocialPM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SocialPM.  If not, see <http://www.gnu.org/licenses/>.
 *  
 * For OEMs, ISVs, and VARs who distribute SocialPM with their products, 
 * host their product online, OcpSoft provides flexible OEM commercial 
 * Licences. 
 * 
 * Optionally, customers may choose a Commercial License. For additional 
 * details, contact OcpSoft (http://ocpsoft.com)
 */

package com.ocpsoft.socialpm.domain.user;

import java.io.Serializable;

import javax.inject.Named;

import com.ocpsoft.socialpm.util.crypt.MD5PasswordEncryptor;
import com.ocpsoft.socialpm.util.crypt.PasswordEncryptor;

/**
 * Encodes the password using username a random salt.
 * 
 * @author lb3
 */
@Named
public class UserPasswordMatchTester implements Serializable
{
   private static final long serialVersionUID = -5443702798033411080L;

   PasswordEncryptor pe = new MD5PasswordEncryptor();

   public UserPasswordMatchTester()
   {
   }

   public boolean passwordMatches(final User user, final String password)
   {
      String encodedPassword = pe.encodePassword(password, user.getUsername());
      return encodedPassword.equals(user.getPassword());
   }

}

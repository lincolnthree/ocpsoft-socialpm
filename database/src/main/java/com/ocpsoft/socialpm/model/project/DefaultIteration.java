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

package com.ocpsoft.socialpm.model.project;

import com.ocpsoft.socialpm.domain.NoSuchObjectException;
import com.ocpsoft.socialpm.domain.project.iteration.Iteration;
import com.ocpsoft.socialpm.domain.project.iteration.IterationStatistics;
import com.ocpsoft.socialpm.util.Dates;

public class DefaultIteration implements DailyStatsUpdater
{
   public static DefaultIteration getInstance()
   {
      return new DefaultIteration();
   }

   @Override
   public boolean shouldUpdate(final Iteration iteration)
   {
      if (iteration.isDefault())
      {
         return true;
      }
      return false;
   }

   @Override
   public void update(final Iteration iteration)
   {
      IterationStatistics stat;
      try
      {
         stat = iteration.getStatistics(Dates.now());
         new StatsCalculator().update(iteration, stat);
      }
      catch (NoSuchObjectException e)
      {
         stat = new StatsCalculator().calculate(iteration);
         stat.setDate(Dates.now());
         stat.setIteration(iteration);
         iteration.getStatistics().add(stat);
      }
   }
}

/**
 * This file is part of libRibbonApp library (check README).
 * Copyright (C) 2013 Stanislav Nepochatov
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
**/

package AppComponents;

/**
 * Network command chain listener;
 * @author Stanislav Nepochatov
 */
public abstract class Listener {
    
    /**
     * Default constructor;
     * @param givenComm command to listen;
     */
    public Listener(String givenComm) {
        COMM_NAME = givenComm;
    }
    
    /**
     * Command name which listener is waiting;
     */
    public String COMM_NAME;
    
    /**
     * Main method of listener.<br>
     * <br>
     * Should be overrided.
     */
    public abstract void exec(String args);
}

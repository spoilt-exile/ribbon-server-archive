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

package ribbonclient;

/**
 * libRibbonApp.AppComponents.NetWorker child class with listensers.
 * @see AppComponents.NetWorker
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
 */
public class ClientWorker extends AppComponents.NetWorker {
    
    /**
     * Default constructor.
     * @param givenApp linked application object;
     */
    public ClientWorker(AppComponents.RibbonApplication givenApp) {
        super(givenApp);
    }
    
    @Override
    public AppComponents.Listener[] getProtocol() {
        return new AppComponents.Listener[] {
            
            /**
             * [SPECIAL] RIBBON_GCTL_FORCE_LOGIN
             * Login request listener.
             */
            new AppComponents.Listener("RIBBON_GCTL_FORCE_LOGIN") {

                @Override
                public void exec(String args) {
                    currentApplication.log(2, "потребує авторізацію!");
                }
            },
            
            /**
             * [LEVEL_3] RIBBON_UCTL_LOAD_INDEX
             * Load new message listener.
             */
            new AppComponents.Listener("RIBBON_UCTL_LOAD_INDEX") {

                @Override
                public void exec(String args) {
                    MessageClasses.MessageEntry newEntry = new MessageClasses.MessageEntry(args);
                    MessageStore.messageIndex.add(newEntry);
                    for (String DIR : newEntry.DIRS) {
                        DirClasses.DirEntryUI.addIndexToDir(DIR, newEntry.INDEX);
                        if (RibbonClient.clientWindow.currDirectory.FULL_DIR_NAME.equals(DIR)) {
                            new Thread () {
                            @Override
                                public void run() {
                                    RibbonClient.clientWindow.refreshMessageList();
                                };
                            }.start();
                        }
                    }
                }
            },
            
            
            /**
             * [LEVEL_3] RIBBON_UCTL_UPDATE_INDEX
             * Load modified message listener.
             */
            new AppComponents.Listener("RIBBON_UCTL_UPDATE_INDEX") {

                @Override
                public void exec(String args) {
                    MessageClasses.MessageEntry modEntry = new MessageClasses.MessageEntry(args);
                    MessageClasses.MessageEntry exsEntry = MessageStore.getMessageEntryByIndex(modEntry.INDEX);
                    for (String exsDir : exsEntry.DIRS) {
                        DirClasses.DirEntryUI.removeIndexFromDir(exsDir, exsEntry.INDEX);
                    }
                    exsEntry.modifyMessageEntry(modEntry);
                    for (String modDir : modEntry.DIRS) {
                        DirClasses.DirEntryUI.addIndexToDir(modDir, modEntry.INDEX);
                        if (RibbonClient.clientWindow.currDirectory.FULL_DIR_NAME.equals(modDir)) {
                            new Thread () {
                            @Override
                                public void run() {
                                    RibbonClient.clientWindow.refreshMessageList();
                                }
                            }.start();
                        }
                    }
                }
            },
            
            /**
             * [LEVEL_3] RIBBON_UCTL_DELETE_INDEX
             * Delete message.
             */
            new AppComponents.Listener("RIBBON_UCTL_DELETE_INDEX") {

                @Override
                public void exec(String args) {
                    MessageClasses.MessageEntry delEntry = MessageStore.getMessageEntryByIndex(args);
                    MessageStore.messageIndex.remove(delEntry);
                    for (String delDir : delEntry.DIRS) {
                        DirClasses.DirEntryUI.removeIndexFromDir(delDir, delEntry.INDEX);
                        if (RibbonClient.clientWindow.currDirectory.FULL_DIR_NAME.equals(delDir)) {
                            new Thread () {
                            @Override
                                public void run() {
                                    RibbonClient.clientWindow.refreshMessageList();
                                }
                            }.start();
                        }
                    }
                }
            }
        };
    }
}

/**
 * This file is part of RibbonClient application (check README).
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
 * Main class
 * @author Stanislav Nepochatov
 */
public class RibbonClient {
    
    /**
     * Name of application.
     */
    private static String NAME = "RibbonClient";
    
    /**
     * Local application name.
     */
    private static String LNAME = "Клієнт системи \"Стрічка\"";
    
    /**
     * Main window;
     */
    public static mainFrame clientWindow;
    
    /**
     * Application handle object;
     */
    public static AppComponents.RibbonApplication ClientApplication;

    /**
     * Main method;
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientApplication = new AppComponents.RibbonApplication(NAME, LNAME, AppComponents.RibbonApplication.ApplicationRole.CLEINT);
        ClientApplication.connect(ClientWorker.class, null);
        loadDirs();
        MessageStore.init();
        clientWindow = new mainFrame();
        clientWindow.setVisible(true);
    }
    
    /**
     * Load directories from server;
     */
    public static void loadDirs() {
        String[] rawDirs = ClientApplication.appWorker.sendCommandWithCollect("RIBBON_GET_DIRS:").split("\n");
        for (String dirLine : rawDirs) {
            DirClasses.DirSchema rawSchema = new DirClasses.DirSchema(Generic.CsvFormat.parseDoubleStruct(dirLine)[1]);
            DirClasses.DirEntryUI.rootDir.insertDir("",rawSchema.FULL_DIR_NAME , rawSchema);
        }
    }
}

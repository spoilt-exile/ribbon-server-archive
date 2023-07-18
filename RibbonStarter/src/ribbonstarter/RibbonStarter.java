/**
* This file is part of RibbonStarter application (check README).
* Copyright (C) 2012-2013 Stanislav Nepochatov
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
**/

package ribbonstarter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.JOptionPane;

/**
 * Main starter class;
 * @author Stanislav Nepochatov
 */
public class RibbonStarter {
    
    /**
     * Main window variable.
     */
    public static StarterFrame starterWindow = new StarterFrame();
    
    /**
     * Current running worker;
     */
    public static Thread starterWorker;
    
    /**
     * Main server starter thread: launch and control execution.
     */
    public static class ServerWorker implements Runnable {

        @Override
        public void run() {
            if (new java.io.File("RibbonServer.jar").exists()) {
                java.lang.Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec("java -jar RibbonServer.jar");
                } catch (java.io.IOException ex) {
                    JOptionPane.showMessageDialog(null, "Неможливо запустити сервер!", "Помилка!", JOptionPane.ERROR_MESSAGE);
                }
                ServerPrinter currPrinter = new ServerPrinter(proc.getInputStream());
                if (!currPrinter.inited) {
                    proc.destroy();
                    return;
                } else {
                    new Thread(currPrinter).start();
                }
                starterWindow.setExecution(true);
                while (true) {
                    try {
                        Thread.sleep(500000);
                    } catch (InterruptedException ex) {
                        proc.destroy();
                        starterWindow.setExecution(false);
                        return;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Файл RibbonServer.jar не знайдено!", "Помилка!", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    /**
     * Server stdout printer thread;
     */
    public static class ServerPrinter implements Runnable {
        
        /**
         * Stdout server reader.
         */
        private java.io.BufferedReader stdOut;
        
        /**
         * Inited state flag (require to stop execution if initialization failed).
         */
        public Boolean inited = false;
        
        /**
         * Main constructor.
         * @param givenOut stdout inputstream;
         */
        ServerPrinter(java.io.InputStream givenOut) {
            try {
                this.stdOut = new java.io.BufferedReader(new java.io.InputStreamReader(givenOut));
                inited = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Неможливо прочести вивід від сервера!", "Помилка!", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        @Override
        public void run() {
            while (true) {
                String outLine = null;
                try {
                    outLine = stdOut.readLine();
                    if (outLine == null) {
                        return;
                    }
                    starterWindow.appendToLog(outLine);
                } catch (java.io.IOException ex) {
                    JOptionPane.showMessageDialog(null, "Неможливо прочести вивід від сервера!", "Помилка!", JOptionPane.ERROR_MESSAGE);
                    starterWindow.setExecution(false);
                    starterWorker.interrupt();
                    return;
                }
            }
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        starterWindow.setVisible(true);
    }
}

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
 * Ribbon client application class;
 * @author Stanislav Nepochatov
 */
public class RibbonApplication {
    
    /**
     * Path to config files;
     */
    private String CONFIG_PATH;
    
    /**
     * Name of main config file;
     */
    private final String MAIN_CONFIG_NAME = "RibbonApplication.properties";
    
    /**
     * Server IP to connect;
     */
    public String SERVER_IP;
    
    /**
     * Server port to connect;
     */
    public Integer SERVER_PORT;
    
    /**
     * Current login;
     */
    public String CURR_LOGIN;
    
    /**
     * Name of application
     */
    public String APP_NAME;
    
    /**
     * Localizated name of application
     */
    public String APP_LNAME;
    
    /**
     * Saved configuration;
     */
    public java.util.Properties ApplicationProperties = new java.util.Properties();
    
    /**
     * Enumeration of application rolse in the system;
     */
    public static enum ApplicationRole {
        CLEINT,
        CONTROL
    }
    
    /**
     * Current role of this application class instance;
     */
    private ApplicationRole CURRENT_ROLE;
    
    /**
     * Log file object;
     */
    private java.io.File logFile;
    
    /**
     * Date format
     */
    private java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    
    /**
     * NetWorker object reference;
     */
    public AppComponents.NetWorker appWorker;
    
    /**
     * Class pointer to NetWorker child class;
     */
    public Class<AppComponents.NetWorker> appWorkerClass;
    
    /**
     * Application initiation flag.
     */
    public Boolean isInited = false;
    
    /**
     * Main constructor
     * @param appName name of application wich will be used;
     * @param localName localizated name of application;
     * @param givenRole given role of application;
     */
    public RibbonApplication(String appName, String localName, RibbonApplication.ApplicationRole givenRole) {
        this.CONFIG_PATH = System.getProperty("user.home") + "/.Ribbon";
        new java.io.File(CONFIG_PATH).mkdirs();
        this.APP_NAME = appName;
        this.APP_LNAME = localName;
        this.CURRENT_ROLE = givenRole;
        this.initLogFile();
        this.log(3, "Програма \'" + this.APP_LNAME + "\' запущено. Роль:" + this.CURRENT_ROLE.name());
        this.initMainProperties();
        this.log(3, "налаштування завершено");
    }
    
    /**
     * Connect to server and init network;
     */
    public void connect(Class workerClass, Runnable postHandler) {
        this.appWorkerClass = workerClass;
        try {
            this.appWorker = appWorkerClass.getConstructor(RibbonApplication.class).newInstance(this);
        } catch (Exception ex) {
            this.log(0, "помилка опрацювання класу: " + appWorkerClass.getName());
            ex.printStackTrace();
            System.exit(7);
        }
        UIComponents.LoginWindow loginFrame = new UIComponents.LoginWindow(this , postHandler);
        if ("1".equals(this.ApplicationProperties.getProperty("remember_session")) && this.ApplicationProperties.getProperty("session_id") != null && this.appWorker.isAlive) {
            String respond = this.appWorker.sendCommandWithReturn("RIBBON_NCTL_RESUME:" + this.ApplicationProperties.getProperty("session_id"));
            if (respond.equals("OK:")) {
                loginFrame.runPost();
                loginFrame = null;
                String userCsv = this.appWorker.sendCommandWithReturn("RIBBON_NCTL_GET_USERNAME:");
                if (userCsv.startsWith("RIBBON_ERROR:")) {
                    this.reportError(userCsv);
                } else {
                    this.CURR_LOGIN = Generic.CsvFormat.complexParseLine(userCsv, 2, 1).get(0)[0];
                }
                this.isInited = true;
                return;
            }
        }
        loginFrame.setVisible(true);
        loginFrame.toFront();
        loginFrame.repaint();
        loginFrame.waitForClose();
        this.isInited = true;
    }
    
    /**
     * Get current date with default date format
     * @return current date
     */
    private String getCurrentDate() {
        java.util.Date now = new java.util.Date();
        String strDate = dateFormat.format(now);
        return strDate;
    }
    
    /**
     * Initiate log file;
     */
    private void initLogFile() {
        this.logFile = new java.io.File(this.CONFIG_PATH + "/" + this.renderAppFilename("Log"));
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (java.io.IOException ex) {
                this.logFile = null;
                this.log(0, "Неможливо створити файл журналу!");
                System.exit(1);
            }
        } else {
            if (this.logFile.length() > (200 * 1024)) {
                try {
                    java.nio.file.Files.delete(this.logFile.toPath());
                } catch (java.io.IOException ex) {
                    this.logFile = null;
                    this.log(0, "Неможливо відчистити файл журналу!");
                    System.exit(1);
                }
                this.logFile = null;
            }
        }
    }
    
    /**
     * Init main application properties or create new one;
     */
    private void initMainProperties() {
        java.io.File mainProps = new java.io.File(this.CONFIG_PATH + "/" + this.MAIN_CONFIG_NAME);
        if (!mainProps.exists()) {
            try {
                this.log(2, "Файл конфігурації не знайдено. Створюю...");
                mainProps.createNewFile();
                java.util.Properties tempProps = new java.util.Properties();
                tempProps.load(RibbonApplication.class.getResourceAsStream("RibbonApplication_sample.properties"));
                tempProps.store(new java.io.FileWriter(mainProps), null);
                this.log(3, "Нова конфігурація створена. Потрібно налаштування.");
            } catch (java.io.IOException ex) {
                this.log(0, "Неможливо створити файл конфігурації!");
                System.exit(2);
            } finally {
                UIComponents.settingsDialog propDialog = new UIComponents.settingsDialog(null, true, ApplicationProperties);
                propDialog.setVisible(true);
                try {
                    this.ApplicationProperties.store(new java.io.FileWriter(mainProps), null);
                } catch (java.io.IOException ex) {
                    this.log(0, "Неможливо зберегти файл конфігурації!");
                    System.exit(2);
                }
                initMainProperties();
            }
        } else {
            try {
                this.ApplicationProperties.load(new java.io.FileInputStream(mainProps));
                this.SERVER_IP = this.ApplicationProperties.getProperty("server_address");
                this.SERVER_PORT = Integer.parseInt(this.ApplicationProperties.getProperty("server_port"));
            } catch (java.io.IOException ex) {
                this.log(0, "Неможливо прочитати файл конфігурації!");
                System.exit(2);
            }
        }
    }
    
    /**
     * Update main Ribbon properties file;
     */
    public void updateProperties() {
        java.io.File mainProps = new java.io.File(this.CONFIG_PATH + "/" + this.MAIN_CONFIG_NAME);
        try {
            this.ApplicationProperties.store(new java.io.FileWriter(mainProps), null);
        } catch (java.io.IOException ex) {
            this.log(0, "Неможливо зберегти файл конфігурації!");
            System.exit(2);
        }
    }
    
    /**
     * <p>Render filename for separate application.</p>
     * 
     * <p><b>Example:</b><br>
     * Log -> $APP_NAME.Log
     * </p>
     * @param postfix desired filename for application file;
     * @return 
     */
    private String renderAppFilename(String postfix) {
        return (this.APP_NAME + "." + postfix);
    }
    
    /**
     * Write message to log
     * @param level level of message
     * @param message message itself
     */
    public synchronized void log(Integer level, String message) {
        String typeStr = "";
        switch (level) {
            case 0:
                typeStr = "КРИТИЧНА ПОМИЛКА";
                break;
            case 1:
                typeStr = "ПОМИЛКА";
                break;
            case 2:
                typeStr = "попередження";
                break;
            case 3:
                typeStr = "повідомлення";
                break;
        }
        String compiledMessage = this.getCurrentDate() + " [" + this.APP_NAME + "] " + typeStr + ": '" + message + "';";
        System.out.println(compiledMessage);
        if (logFile != null) {
            try (java.io.FileWriter logWriter = new java.io.FileWriter(logFile, true)) {
                logWriter.write(compiledMessage + "\n");
            } catch (Exception ex) {
                logFile = null;
                log(0, "неможливо записати файл журналу!");
                System.exit(1);
            }
        }
        if (level < 2) {
            final javax.swing.JPanel panel = new javax.swing.JPanel();
            javax.swing.JOptionPane.showMessageDialog(panel, message, this.APP_LNAME, javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Report error recieved from server;
     * @param message raw server message;
     */
    public void reportError(String message) {
        this.log(1, "Помилка системи:\n" + Generic.CsvFormat.parseDoubleStruct(message)[1]);
    }
    
    /**
     * Get hash sum of given string.
     * @param givenStr given string;
     * @return md5 hash sum representation;
     */
    public String getHash(String givenStr) {
        StringBuffer hexString = new StringBuffer();
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(givenStr.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (Exception ex) {
            this.log(1, "Неможливо добути хеш-суму!");
        }
        return hexString.toString();
    }
    
    /**
     * Get existed properties or create new one with given stream;
     * @param fileName file postfix to read;
     * @param newFileStream stream of template properties in app jar;
     * @return loaded properties;
     */
    public java.util.Properties getProperties(String fileName, java.io.InputStream newFileStream) {
        java.util.Properties gettedProps = new java.util.Properties();
        java.io.File propsFile = new java.io.File(this.CONFIG_PATH + "/" + this.renderAppFilename(fileName));
        if (propsFile.exists()) {
            try {
                gettedProps.load(new java.io.FileInputStream(propsFile));
            } catch (java.io.IOException ex) {
                this.log(1, "неможливо прочитати файл налаштуваннь (" + propsFile.getAbsolutePath() + ")");
            }
        } else {
           try {
               this.log(2, "створюю новий файл налаштуваннь (" + propsFile.getAbsolutePath() + ")");
               gettedProps.load(newFileStream);
               gettedProps.store(new java.io.FileOutputStream(propsFile), null);
            } catch (java.io.IOException ex) {
                this.log(1, "неможливо створити файл налаштуваннь (" + propsFile.getAbsolutePath() + ")");
            }
        }
        return gettedProps;
    }
    
    /**
     * Store properties.
     * @param fileName file name postfix to save;
     * @param givenProps properties to save;
     */
    public void storeProperties(String fileName, java.util.Properties givenProps) {
        java.io.File storeFile = new java.io.File(this.CONFIG_PATH + "/" + this.renderAppFilename(fileName));
        try {
            this.log(2, "збереження файлу налаштуваннь (" + storeFile.getAbsolutePath() + ")");
            givenProps.store(new java.io.FileOutputStream(storeFile), null);
        } catch (java.io.IOException ex) {
            this.log(1, "неможливо створити файл налаштуваннь (" + storeFile.getAbsolutePath() + ")");
        }
    }
}

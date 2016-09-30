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
 * Message loader and handler class;
 * @author Stanislav Nepochatov <spoilt.exile@gmail.com>
 */
public final class MessageStore {
    
    /**
     * Storage of message entries.
     */
    public static java.util.ArrayList<MessageClasses.MessageEntry> messageIndex = new java.util.ArrayList();
    
    /**
     * Load messages from network.
     */
    public static void init() {
        String rawMsg = RibbonClient.ClientApplication.appWorker.sendCommandWithCollect("RIBBON_LOAD_BASE_FROM_INDEX:0");
        if (rawMsg.startsWith("END:") || rawMsg.isEmpty()) {
            return;
        }
        for (String msgLine : rawMsg.split("\n")) {
            MessageClasses.MessageEntry newEntry = new MessageClasses.MessageEntry(Generic.CsvFormat.parseDoubleStruct(msgLine)[1]);
            messageIndex.add(newEntry);
            for (Integer dirIndex = 0; dirIndex < newEntry.DIRS.length; dirIndex++) {
                DirClasses.DirEntryUI.addIndexToDir(newEntry.DIRS[dirIndex], newEntry.INDEX);
            }
        }
    }
    
    /**
     * Get string rendered header (DATE + HEADER).
     * @param givenIndex index of message;
     * @return string with date and message header;
     */
    public static String getHeader(String givenIndex) {
        MessageClasses.MessageEntry matchedMessage = MessageStore.getMessageEntryByIndex(givenIndex);
        return matchedMessage.DATE + "  " + matchedMessage.HEADER;
    }
    
    /**
     * Get message entry object by index or null if message is absent.
     * @param givenIndex index of message for search
     * @return message entry object or null
     */
    public static MessageClasses.MessageEntry getMessageEntryByIndex(String givenIndex) {
        java.util.ListIterator<MessageClasses.MessageEntry> messageIter = MessageStore.messageIndex.listIterator();
        while (messageIter.hasNext()) {
            MessageClasses.MessageEntry currEntry = messageIter.next();
            if (currEntry.INDEX.equals(givenIndex)) {
                return currEntry;
            }
        }
        return null;
    }
    
    /**
     * Return message entry by dir name and index in specified dir.
     * @param dir directory name for search;
     * @param index index of message in specified dir index;
     * @return finded message or null (not found).
     */
    public static MessageClasses.MessageEntry getMessageByDirIndex(String dir, Integer index) {
        if (dir.isEmpty() || index.equals(-1)) {
            return null;
        } else {
            DirClasses.DirEntry findedDir = DirClasses.DirEntryUI.rootDir.returnEndDir("", dir);
            if (findedDir == null) {
                return null;
            } else {
                return getMessageEntryByIndex(findedDir.DIR_INDEXCES.get(index));
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Student
 */
public class MessageManager {

    public static void main(String[] args) {
    private Message[] sentMessages = new Message[0];
    private Message[] disregardedMessages = new Message[0];
    private Message[] storedMessages = new Message[0];
    private String[] messageHashes = new String[0];
    private String[] messageIDs = new String[0];

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void addMessage(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null.");
        }

        // Prevent duplicate IDs in the manager.
        if (findById(message.getMessageId()) != null) {
            return;
        }

        String flag = message.getFlag() == null
                ? "Pending" : message.getFlag();

        switch (flag.toLowerCase()) {
            case "sent":
                sentMessages = append(sentMessages, message);
                break;
            case "stored":
                storedMessages = append(storedMessages, message);
                break;
            case "disregard":
            case "disregarded":
                disregardedMessages = append(disregardedMessages, message);
                break;
            default:
                // Pending messages are not placed in a status array.
                return;
        }

        messageIDs = append(messageIDs, message.getMessageId());
        messageHashes = append(messageHashes, message.getMessageHash());
    }

    private Message[] append(Message[] array, Message item) {
        Message[] result = Arrays.copyOf(array, array.length + 1);
        result[array.length] = item;
        return result;
    }

    private String[] append(String[] array, String item) {
        String[] result = Arrays.copyOf(array, array.length + 1);
        result[array.length] = item;
        return result;
    }

    /** Loads the JSON array and rebuilds the Stored Messages, ID and hash arrays. */
    public void loadStoredMessages(Path jsonFile) throws IOException {
        if (!Files.exists(jsonFile)) {
            storedMessages = new Message[0];
            rebuildIndexes();
            return;
        }

        try (Reader reader = Files.newBufferedReader(jsonFile, StandardCharsets.UTF_8)) {
            Type listType = new TypeToken<List<Message>>() {}.getType();
            List<Message> loaded = gson.fromJson(reader, listType);
            storedMessages = loaded == null
                    ? new Message[0] : loaded.toArray(new Message[0]);
        }
        rebuildIndexes();
    }

    public void saveStoredMessages(Path jsonFile) throws IOException {
        Path parent = jsonFile.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        try (Writer writer = Files.newBufferedWriter(jsonFile, StandardCharsets.UTF_8)) {
            gson.toJson(storedMessages, writer);
        }
    }

    private void rebuildIndexes() {
        List<String> ids = new ArrayList<>();
        List<String> hashes = new ArrayList<>();

        addIndexes(ids, hashes, sentMessages);
        addIndexes(ids, hashes, disregardedMessages);
        addIndexes(ids, hashes, storedMessages);

        messageIDs = ids.toArray(new String[0]);
        messageHashes = hashes.toArray(new String[0]);
    }

    private void addIndexes(List<String> ids, List<String> hashes, Message[] messages) {
        for (Message message : messages) {
            ids.add(message.getMessageId());
            hashes.add(message.getMessageHash());
        }
    }

    public String displayStoredSendersAndRecipients() {
        StringBuilder output = new StringBuilder();
        if (storedMessages.length == 0) {
            return "There are no stored messages.";
        }
        for (Message message : storedMessages) {
            output.append("Sender: ").append(senderFor(message))
                  .append("\nRecipient: ").append(message.getRecipientCell())
                  .append("\n\n");
        }
        return output.toString();
    }

    // The assignment's test data identifies the developer as the sender.
    // In this QuickChat version the developer number is captured by ChatApp.
    private String developerNumber = "Not recorded";

    public void setDeveloperNumber(String developerNumber) {
        this.developerNumber = developerNumber;
    }

    private String senderFor(Message message) {
        return developerNumber;
    }

    public Message displayLongestStoredMessage() {
        if (storedMessages.length == 0) {
            return null;
        }
        Message longest = storedMessages[0];
        for (Message message : storedMessages) {
            if (message.getMessageText().length() > longest.getMessageText().length()) {
                longest = message;
            }
        }
        return longest;
    }

    /** Searches all status arrays because a message ID can refer to any message. */
    public Message searchMessageByID(String id) {
        return findById(id);
    }

    private Message findById(String id) {
        if (id == null) return null;
        for (Message message : sentMessages) {
            if (id.equals(message.getMessageId())) return message;
        }
        for (Message message : storedMessages) {
            if (id.equals(message.getMessageId())) return message;
        }
        for (Message message : disregardedMessages) {
            if (id.equals(message.getMessageId())) return message;
        }
        return null;
    }

    /** Searches stored messages only, as specified in Part 3(d). */
    public List<Message> searchMessagesByRecipient(String recipient) {
        List<Message> matches = new ArrayList<>();
        for (Message message : storedMessages) {
            if (message.getRecipientCell() != null &&
                    message.getRecipientCell().equals(recipient)) {
                matches.add(message);
            }
        }
        return matches;
    }

    public boolean deleteMessageByHash(String hash) {
        for (int i = 0; i < storedMessages.length; i++) {
            if (storedMessages[i].getMessageHash().equals(hash)) {
                storedMessages = removeAt(storedMessages, i);
                rebuildIndexes();
                return true;
            }
        }
        return false;
    }

    private Message[] removeAt(Message[] array, int index) {
        Message[] result = new Message[array.length - 1];
        for (int old = 0, next = 0; old < array.length; old++) {
            if (old != index) result[next++] = array[old];
        }
        return result;
    }

    public String displayMessageReport() {
        StringBuilder report = new StringBuilder();
        report.append("========== STORED MESSAGE REPORT ==========\n");
        if (storedMessages.length == 0) {
            report.append("There are no stored messages.\n");
        }
        for (Message message : storedMessages) {
            report.append("Message Hash: ").append(message.getMessageHash()).append('\n')
                  .append("Message ID: ").append(message.getMessageId()).append('\n')
                  .append("Recipient: ").append(message.getRecipientCell()).append('\n')
                  .append("Message: ").append(message.getMessageText()).append('\n')
                  .append("Flag: ").append(message.getFlag()).append("\n\n");
        }
        report.append("===========================================\n");
        return report.toString();
    }

    public Message[] getSentMessages() { return Arrays.copyOf(sentMessages, sentMessages.length); }
    public Message[] getDisregardedMessages() { return Arrays.copyOf(disregardedMessages, disregardedMessages.length); }
    public Message[] getStoredMessages() { return Arrays.copyOf(storedMessages, storedMessages.length); }
    public String[] getMessageHashes() { return Arrays.copyOf(messageHashes, messageHashes.length); }
    public String[] getMessageIDs() { return Arrays.copyOf(messageIDs, messageIDs.length); }
    }
 
}


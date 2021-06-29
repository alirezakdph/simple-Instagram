package Client.Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.UUID;

public class PersonalChat
{
    private String chatID;
    private ArrayList<String> members;
    private ArrayList<Message> messageList;

    public PersonalChat(String user1, String user2) {
        messageList = new ArrayList<>();
        members = new ArrayList<>(2);
        members.add(user1);
        members.add(user2);
        chatID = IDBuilder();
    }

    public PersonalChat() {}

    public void addMessage(Message message) { messageList.add(message); }

    public ArrayList<String> getMembers() { return members; }

    public ArrayList<Message> getMessageList() { return messageList; }

    public String getChatID() { return chatID; }

    public void setChatID(String chatID) { this.chatID = chatID; }

    public void setMembers(ArrayList<String> members) { this.members = members; }

    public void setMessageList(ArrayList<Message> messageList) { this.messageList = messageList; }

    public String IDBuilder() { return members.get(0) + " " + members.get(1) + UUID.randomUUID().toString(); }

    public DBObject createChatPersonalDBObject() {
        return new BasicDBObject()
                .append("ChatID",getChatID())
                .append("Members",getMembers())
                .append("MessageList",getMessageList());
    }

    public DBObject createChatIDDBObject() {
        return new BasicDBObject()
                .append("ChatID",getChatID())
                .append("Members",getMembers())
                .append("MessageList",getMessageList());
    }

    public static Server.Model.PersonalChat parsePersonalChatDBObject(DBObject object)
    {
        Server.Model.PersonalChat chat = new Server.Model.PersonalChat();
        chat.setChatID((String) object.get("ChatID"));
        chat.setMembers((ArrayList<String>) object.get("Members"));
        chat.setMessageList((ArrayList<Server.Model.Message>) object.get(("MessageList")));
        return chat;
    }

}

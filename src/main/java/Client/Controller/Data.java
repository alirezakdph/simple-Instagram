package Client.Controller;

import Client.Model.Chat;
import Client.Model.Message;
import Client.Model.Post;
import Client.Model.User;
import java.util.List;

public class Data
{
    public String clientUsername;
    public List<String> usernames;
    public String dataString;
    public String postID;
    public String text;
    public User user;
    public Post post;
    public List<Post> posts;
    public Chat chat;
    public Message message;
    public boolean flag;

    public Data() {}

    public Data (String clientUsername) {
        this.clientUsername = clientUsername;
    }

    //used by all Requests and Responses with just ONE String of data other than the client's username. (e.g. login)
    public static class Basic extends Data
    {
        public Basic(String clientUsername, String dataString) {
            super(clientUsername);
            this.dataString = dataString;
        }
    }

    //only used by the server so there's no need to add the client's username. (e.g. returning the list of followers)
    public static class UsernameList extends Data
    {
        public UsernameList(String clientUsername, List<String> usernames) {
            super(clientUsername);
            this.usernames.addAll(usernames);
        }
    }

    public static class Comment extends Data
    {
        public Comment(String clientUsername, String postID, String text) {
            super(clientUsername);
            this.postID = postID;
            this.text = text;
        }
    }

    public static class UserData extends Data
    {
        public UserData(User user) {
            this.user = user;
        }
    }

    public static class PostSingle extends Data
    {
        public PostSingle(String clientUsername, Post post) {
            super(clientUsername);
            this.post = post;
        }
    }

    public static class PostList extends Data
    {
        public PostList(List<Post> posts) {
            this.posts.addAll(posts);
        }
    }

    public static class ChatData extends Data
    {
        public ChatData(String clientUsername, Chat chat) {
            super(clientUsername);
            this.chat = chat;
        }
    }

    public static class MessageData extends Data
    {
        public MessageData(String clientUsername, Message message) {
            super(clientUsername);
            this.message = message;
        }
    }

    public static class BooleanData extends Data
    {
        public BooleanData (boolean flag) {
            this.flag = flag;
        }

        public BooleanData(String clientUsername, boolean flag) {
            super(clientUsername);
            this.flag = flag;
        }
    }

    public static class Empty extends Data
    {
        public Empty() {}
    }

}

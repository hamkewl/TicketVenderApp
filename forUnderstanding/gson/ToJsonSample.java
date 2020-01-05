/*
	convert Java to JSON
*/

import java.io.*;
import java.util.*;

import com.google.gson.*;

public class ToJsonSample{
	public static void main(String[] args) {
    try{
		  Gson gson = new Gson();
    
  		User user1 = new User("bob@jmail.com", null);	// fullnameにnullをセット
  		User user2 = new User("jeff@jmail.com", "Jeff");
  		List<User> userList = new ArrayList<>();
  		userList.add(user1);
  		userList.add(user2);
  
  		// JavaオブジェクトからJSONへの変換
  		System.out.println("user1: " + gson.toJson(user1));
  		System.out.println("user2: " + gson.toJson(user2));
  
  		// JavaオブジェクトからJSONへの変換：List
  		System.out.println("ユーザーリスト: " + gson.toJson(userList));
  
  		Post newPost = new Post(userList.get(0), "postTitle", "postContent");
  		Comment comment = new Comment("comment_author", "comment_comment");
  		newPost.comments.add(comment);
  		newPost.comments.add(comment);
  
  		// JavaオブジェクトからJSONへの変換：フィールドにListを含むオブジェクト
  		System.out.println("コメント付き投稿: " + gson.toJson(newPost));
  	}
    catch(Exception e){
      e.printStackTrace();
    }
  }
}


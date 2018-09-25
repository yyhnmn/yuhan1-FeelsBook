package com.example.yuhan1_feelsbook;

// Comment too long exception: comment should be limited within 100 characters
class CommentTooLongException extends Exception {
    CommentTooLongException(){
        super("The comment is too long! Please keep your comment within 100 characters");
    }
}

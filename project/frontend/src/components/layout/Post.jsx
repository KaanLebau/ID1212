import React from "react";
import "../../assets/styles/Post.css";

function Post({ post }) {
  return (
    <div className="post-detail-container">
      <div className="post-detail">
        <h1 className="post-title">{post.title}</h1>
        <div className="post-author">{`by ${post.comments[0].author}`}</div>
        <p className="post-content">{post.content}</p>
        <div className="comments-section">
          {post.comments.map((comment) => (
            <div key={comment.text} className="comment">
              <div className="comment-author">{comment.author}</div>
              <div className="comment-text">{comment.text}</div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Post;

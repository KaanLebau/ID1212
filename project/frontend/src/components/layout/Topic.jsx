import React from "react";
import "../../assets/styles/Topic.css";

function Topic({topic, posts, handlePostClick}) {
  return (
    <>
      <p className="topic-title">{topic.topicName}</p>
    <div className="topic-container">
      {posts.map((post, index) => (
        <div key={post.id} onClick={() => handlePostClick(post.id)} className="post-container">
          <div className="post-header">
            <span className="post-author">{post.comments[0].author}</span>
            <h2 className="post-title">{post.title}</h2>
          </div>
          <div className="post-footer">
            <span className="post-date">{post.created}</span>
            <span className="post-comments">
              {post.comments.length} comments
            </span>
          </div>
        </div>
      ))}
      <div onClick={() => handlePostClick(0)} className="post-container create-post-card">
        <div className="create-post-content">
          <div className="create-post-plus-sign">+</div>
          <div className="create-post-text">Create New Post</div>
        </div>
      </div>
    </div>
    </>
  );
}

export default Topic;
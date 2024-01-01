import React from "react";
import "../../assets/styles/Post.css";
import { useState, useEffect } from "react";
import { getUser } from "../../services/ApiService";

function Post({ user, post, comments, handleUpdatePost, handleDeletePost, handleCreateComment, handleUpdateComment, handleDeleteComment }) {
  const [isEditing, setIsEditing] = useState(false);
  const [editedContent, setEditedContent] = useState(post.content);
  const [editedTitle, setEditedTitle] = useState(post.title);
  const [editingComments, setEditingComments] = useState({});
  const [userDisplayNames, setUserDisplayNames] = useState({});

  useEffect(() => {
    if (!userDisplayNames[post.userId]) {
      getUser(post.userId).then((user) => {
        setUserDisplayNames((prevNames) => ({
          ...prevNames,
          [post.userId]: user ? user.displayName : "Anonymous",
        }));
      });
    }

    comments.forEach((comment) => {
      if (!userDisplayNames[comment.userId]) {
        getUser(comment.userId).then((user) => {
          setUserDisplayNames((prevNames) => ({
            ...prevNames,
            [comment.userId]: user ? user.displayName : "Anonymous",
          }));
        });
      }
    });
  }, [post, comments]);

  const handleCommentSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const comment = formData.get("comment");
    handleCreateComment(comment);
    event.target.reset();
  };

  const handleTitleChange = (e) => {
    setEditedTitle(e.target.value);
  };

  const handleContentChange = (e) => {
    setEditedContent(e.target.value);
  };

  const handlePostUpdateSubmit = () => {
    handleUpdatePost(editedTitle, editedContent);
    setIsEditing(false);
  };

  const handlePostDelete = () => {
    handleDeletePost();
  }

  const handleCommentUpdateSubmit = (commentId) => {
    handleUpdateComment(commentId, editedContent);
    setEditingComments((prev) => ({
      ...prev,
      [commentId]: false,
    }));
  }

  const handleCommentDelete = (commentId) => {
    handleDeleteComment(commentId);
    setEditingComments((prev) => ({
      ...prev,
      [commentId]: false,
    }));
  }

  const handleEditToggle = () => {
    setIsEditing(!isEditing);
  };

  const toggleEditComment = (commentId) => {
    setEditingComments((prev) => ({
      ...prev,
      [commentId]: !prev[commentId],
    }));
  };

  return (
    <div className="post-detail-container">
      <div className="post-detail">
        <span className="timestamp-created">{`Created: ${post.created}`}</span>
        <span className="timestamp-updated">{`Updated: ${post.updated}`}</span>
        {isEditing ? (
          <input
            type="text"
            value={editedTitle}
            onChange={handleTitleChange}
            className="post-title-editable"
          />
        ) : (
          <h1 className="post-title">{post.title}</h1>
        )}
        <div className="post-author">{`by ${userDisplayNames[post.userId] || "Loading..."}`}</div>

        {isEditing ? (
          <textarea
            value={editedContent}
            onChange={handleContentChange}
            className="post-content-editable"
            rows={3}
          />
        ) : (
          <p className="post-content">{post.content}</p>
        )}

        {post.userId == user.id && (
          <div className="post-management">
            {!isEditing ? (
              <>
                <span className="post-edit-btn" onClick={handleEditToggle}>
                  Edit
                </span>
                <span className="post-delete-btn" onClick={handlePostDelete}>Delete</span>
              </>
            ) : (
              <>
                <span
                  className="post-submit-edit-btn"
                  onClick={handlePostUpdateSubmit}
                >
                  Submit
                </span>
                <span
                  className="post-abort-edit-btn"
                  onClick={handleEditToggle}
                >
                  Abort
                </span>
              </>
            )}
          </div>
        )}
        <div className="comments-section">
          {comments.map((comment) => (
            <div key={comment.id} className="comment">
              <div className="comment-author">{userDisplayNames[post.userId] || "Loading..."}</div>
              {editingComments[comment.id] ? (
                <textarea
                  defaultValue={comment.comment}
                  className="comment-text-editable"
                  rows={3}
                />
              ) : (
                <div className="comment-text">{comment.comment}</div>
              )}

              <div className="comment-management">
                <div className="comment-buttons">
                  {user.id === comment.user.id && (
                    <>
                      {!editingComments[comment.id] ? (
                        <>
                          <span
                            className="comment-edit-btn"
                            onClick={() => toggleEditComment(comment.id)}
                          >
                            Edit
                          </span>
                          <span
                            className="comment-delete-btn"
                            onClick={() => handleCommentDelete(comment.id)}
                          >
                            Delete
                          </span>
                        </>
                      ) : (
                        <>
                          <span
                            className="comment-submit-edit-btn"
                            onClick={() => handleCommentUpdateSubmit(comment.id)}
                          >
                            Submit
                          </span>
                          <span
                            className="comment-abort-edit-btn"
                            onClick={() => toggleEditComment(comment.id)}
                          >
                            Abort
                          </span>
                        </>
                      )}
                    </>
                  )}
                </div>
                <div className="comment-dates">
                {comment.updated && <span className="comment-updated-date">{`Updated: ${comment.updated}`}</span>}
                  <span className="comment-created-date">{`Created: ${comment.created}`}</span>
                </div>
              </div>
            </div>
          ))}
        </div>
        <form className="new-comment-form" onSubmit={handleCommentSubmit}>
          <textarea
            name="comment"
            className="new-comment-textarea"
            placeholder="Write your comment here..."
            rows={3}
            defaultValue=""
          />
          <button type="submit" className="submit-comment-btn">
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}

export default Post;

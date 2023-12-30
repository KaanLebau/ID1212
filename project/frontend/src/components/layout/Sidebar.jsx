import React, { useState } from 'react';
import PropTypes from 'prop-types';
import '../../assets/styles/Sidebar.css';
import editIcon from '../../components/icons/edit.png';
import deleteIcon from '../../components/icons/trash.png';

function Sidebar({ course, topics, roleId, onTopicSelect, handleCreateTopic, handleUpdateTopic, handleDeleteTopic }) {
  const [editingTopicId, setEditingTopicId] = useState(null);
  const [editedTopicName, setEditedTopicName] = useState('');
  const [isCreating, setIsCreating] = useState(false);
  const [newTopicName, setNewTopicName] = useState('');

  const startEditing = (topic) => {
    setEditingTopicId(topic.id);
    setEditedTopicName(topic.topicName);
    setIsCreating(false);
  };

  const cancelEditing = () => {
    setEditingTopicId(null);
    setEditedTopicName('');
    setIsCreating(false);
  };

  const startCreating = () => {
    setIsCreating(true);
    setEditingTopicId(null);
  };

  const cancelCreating = () => {
    setIsCreating(false);
    setNewTopicName('');
  };

  const saveTopic = () => {
    if (isCreating) {
      handleCreateTopic(newTopicName);
      cancelCreating();
    } else {
      handleUpdateTopic(editingTopicId, editedTopicName);
      cancelEditing();
    }
  };

  const deleteTopic = () => {
    handleDeleteTopic(editingTopicId);
    cancelEditing();
  }

  if (!course) {
    return <div>Loading course...</div>;
  }
  return (
    <aside className="sidebar">
      <div id="course-header">
        <h2>{course.courseId}</h2>
      </div>
      <div className="topics-container">
        {topics.map(topic => (
          <div key={topic.id} className={`topic-item-container ${editingTopicId === topic.id ? 'editing' : ''}`}>
            {editingTopicId === topic.id ? (
              <>
                <input
                  type="text"
                  value={editedTopicName}
                  onChange={(e) => setEditedTopicName(e.target.value)}
                  className="topic-edit-input"
                  onBlur={saveTopic}
                  onKeyDown={(e) => e.key === 'Enter' && saveTopic()}
                />
                <button onClick={saveTopic} className="topic-submit-btn">Submit</button>
              </>
            ) : (
              <a onClick={() => onTopicSelect(course.id, topic.id)} className="topic-item">
                {topic.topicName}
              </a>
            )}

            {roleId <= 2 && (
              <span className="topic-icons">
                <img
                  src={editIcon}
                  alt="Edit"
                  onClick={() => startEditing(topic)}
                  className="topic-edit-icon"
                />
                <img
                  src={deleteIcon}
                  alt="Delete"
                  onClick={() => deleteTopic(topic.id)}
                  className="topic-delete-icon"
                />
              </span>
            )}
          </div>
        ))}
        {isCreating ? (
          <div className="topic-item-container creating">
            <input
              type="text"
              value={newTopicName}
              onChange={(e) => setNewTopicName(e.target.value)}
              className="topic-edit-input"
              autoFocus
            />
            <button onClick={saveTopic} className="topic-submit-btn">Submit</button>
            <button onClick={cancelCreating} className="topic-cancel-btn">Cancel</button>
          </div>
        ) : (
          <a onClick={startCreating} className="create-topic">Create new topic</a>
        )}
      </div>
    </aside>
  );
}

Sidebar.propTypes = {
  course: PropTypes.shape({
    courseId: PropTypes.string.isRequired,
    id: PropTypes.number.isRequired,
  }).isRequired,
  topics: PropTypes.arrayOf(
    PropTypes.shape({
      topicName: PropTypes.string.isRequired,
      id: PropTypes.number.isRequired,
    })
  ).isRequired,
  roleId: PropTypes.number.isRequired,
  onTopicSelect: PropTypes.func.isRequired,
  handleUpdateTopic: PropTypes.func.isRequired,
  handleDeleteTopic: PropTypes.func.isRequired,
};
  
export default Sidebar;

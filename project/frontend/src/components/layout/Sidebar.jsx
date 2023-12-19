import PropTypes from 'prop-types';
import '../../assets/styles/Sidebar.css';

function Sidebar({course, topics, onTopicSelect}) {
    if (!course) {
        return <div>Loading course...</div>;
      }

    return (
        <aside className="sidebar">
            <div id="course-header">
                <h2>{course.name}</h2>
            </div>
            <div className="topics-container">
                {topics.map(topic => (
                    <a key={topic.id} onClick={() => onTopicSelect(topic)} className="topic-item">
                        {topic.subject}
                    </a>
                ))}
            </div>
        </aside>
    );
}

Sidebar.propTypes = {
    course: PropTypes.shape({
      name: PropTypes.string.isRequired,
      id: PropTypes.number.isRequired,
    }).isRequired,
    topics: PropTypes.arrayOf(
      PropTypes.shape({
        subject: PropTypes.string.isRequired,
        id: PropTypes.number.isRequired,
        content: PropTypes.string.isRequired,
        comments: PropTypes.arrayOf(
          PropTypes.shape({
            author: PropTypes.string.isRequired,
            text: PropTypes.string.isRequired,
          })
        ).isRequired,
      })
    ).isRequired,
    onTopicSelect: PropTypes.func,
  };
  
export default Sidebar;

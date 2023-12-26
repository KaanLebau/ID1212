import PropTypes from 'prop-types';
import '../../assets/styles/Sidebar.css';

function Sidebar({course, topics, onTopicSelect}) {
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
                    <a key={topic.id} onClick={() => onTopicSelect(course.id, topic.id)} className="topic-item">
                        {topic.topicName}
                    </a>
                ))}
                <a className="create-topic">Create new topic</a>
            </div>
        </aside>
    );
}

Sidebar.propTypes = {
    course: PropTypes.shape({
      courseName: PropTypes.string.isRequired,
      id: PropTypes.number.isRequired,
    }).isRequired,
    topics: PropTypes.arrayOf(
      PropTypes.shape({
        topicName: PropTypes.string.isRequired,
        id: PropTypes.number.isRequired,
        postList: PropTypes.arrayOf(
          PropTypes.shape({
            //author: PropTypes.string.isRequired,
            //text: PropTypes.string.isRequired,
          })
        ).isRequired,
      })
    ).isRequired,
    onTopicSelect: PropTypes.func,
  };
  
export default Sidebar;

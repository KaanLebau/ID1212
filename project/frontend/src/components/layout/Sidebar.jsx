import '../../assets/styles/Sidebar.css';

function Sidebar({ topics, course }) {
    return (
        <aside className="sidebar">
            <div id="course-header">
                <h2>{course.name}</h2>
            </div>
            <div className="topics-container">
                {topics.map(topic => (
                    <a key={topic.id} href={`#c${course.id}t${topic.id}`} className="topic-item">
                        {topic.subject}
                    </a>
                ))}
            </div>
        </aside>
    );
}

export default Sidebar;

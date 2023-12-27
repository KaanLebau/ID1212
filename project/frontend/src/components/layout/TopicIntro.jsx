import '../../assets/styles/TopicIntro.css';

function TopicIntro({course}) {
    if (!course) {
        return <div>Loading course...</div>;
      }

      console.log(course)
    return (
        <div className="topic-intro">
            <header className="topic-intro-logo">{course.courseId + " - " + course.courseName}</header>
            <p className="topic-intro-description">{course.courseDesc}</p>
            <p className='topic-intro-message'>Please select a topic to get started.</p>
        </div>
    );
}

export default TopicIntro;

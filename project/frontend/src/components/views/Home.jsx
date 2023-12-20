import { useState, useEffect } from 'react';
import Navbar from '../layout/Navbar';
import Sidebar from '../layout/Sidebar';
import Topic from '../layout/Topic';
import '../../assets/styles/Home.css';
import { getCourses, getCourse, getTopics, getTopic, getUser, setUserDisplayName } from '../../services/ApiService';

function Home() {
    const [courses, setCourses] = useState([]);
    const [topics, setTopics] = useState([]);
    const [currentCourse, setCurrentCourse] = useState();
    const [currentTopic, setCurrentTopic] = useState();

    useEffect(() => {
        getCourses().then(setCourses);
      }, []);
    
      useEffect(() => {
        if (currentCourse) {
          getTopics(currentCourse.id).then(setTopics);
          getTopic(1).then(setCurrentTopic);
        }
      }, [currentCourse]);
    
      useEffect(() => {
        if (currentTopic) {
          getTopic(currentTopic.id).then(setCurrentTopic);
        }
      }, [currentTopic]);

    return (
        <div className="home">
        <Navbar courses={courses} onCourseSelect={setCurrentCourse} />

        {!currentCourse &&         
        <header className="introduction">
            Welcome to the forum. Please select one of your courses above to get started.
        </header>}

        {currentCourse && <Sidebar topics={topics} course={currentCourse}onTopicSelect={setCurrentTopic} />}
        {currentTopic && <Topic topic={currentTopic} />}
      </div>
    );
}

export default Home;
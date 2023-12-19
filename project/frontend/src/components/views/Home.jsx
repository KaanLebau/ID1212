import { useState, useEffect } from 'react';
import Navbar from '../layout/Navbar';
import Sidebar from '../layout/Sidebar';
import Topic from '../layout/Topic';
import Introduction from '../layout/Introduction';
import '../../assets/styles/Home.css';
import { getCourses, getCourse, getTopics, getTopic } from '../../services/ApiService';

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
        console.log(currentTopic)
        if (currentTopic) {
          getTopic(currentTopic.id).then(setCurrentTopic);
        }
      }, [currentTopic]);

    return (
        <div className="home">
        <Navbar courses={courses} onCourseSelect={setCurrentCourse} />
        {!currentCourse && <Introduction />}
        {currentCourse && <Sidebar topics={topics} course={currentCourse}onTopicSelect={setCurrentTopic} />}
        {currentTopic && <Topic topic={currentTopic} />}
      </div>
    );
}

export default Home;
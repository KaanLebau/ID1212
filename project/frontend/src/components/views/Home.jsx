import { useState, useEffect } from 'react';
import Navbar from '../layout/Navbar';
import Sidebar from '../layout/Sidebar';
import Topic from '../layout/Topic';
import '../../assets/styles/Home.css';
import { getCourses, getCourse, getTopics, getTopic } from '../../services/ApiService';

function Home() {
    const [courses, setCourses] = useState([]);
    const [topics, setTopics] = useState([]);
    const [currentCourse, setCurrentCourse] = useState(getCourse(0).then());
    const [currentTopic, setCurrentTopic] = useState(console.log(getTopic(0).then()));

    useEffect(() => {
        getCourses().then(setCourses);
      }, []);
    
      useEffect(() => {
        if (currentCourse) {
          getTopics(currentCourse.id).then(setTopics);
          setCurrentTopic(null);
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
        <Sidebar topics={topics} course={currentCourse}onTopicSelect={setCurrentTopic} />
        {currentTopic && <Topic topic={currentTopic} />}
      </div>
    );
}

export default Home;
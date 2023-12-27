import { useState, useEffect } from "react";
import Navbar from "../layout/Navbar";
import Sidebar from "../layout/Sidebar";
import Topic from "../layout/Topic";
import CreateCourse from "../layout/CreateCourse";
import CreateTopic from "../layout/CreateTopic";
import TopicIntro from "../layout/TopicIntro";
import "../../assets/styles/Home.css";
import {
  getCourses,
  getTopics,
  getTopicsByCourse,
  getCommentsByTopic,
} from "../../services/ApiService";
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';


function Home() {
  let navigate = useNavigate();
  let { courseId, topicId } = useParams();
  const [courses, setCourses] = useState([]);
  const [topics, setTopics] = useState([]);
  const [currentTopics, setCurrentTopics] = useState([]);
  const [currentComments, setCurrentComments] = useState([]);

  useEffect(() => {
    getCourses().then(setCourses);
  }, []);

  useEffect(() => {
      getTopics().then(setTopics);
  })

  useEffect(() => {
    if (courseId) {
      getTopicsByCourse(courseId).then(setCurrentTopics);
    }
  }, [courseId]);

  useEffect(() => {
    if (topicId) {
      getCommentsByTopic(topicId).then(setCurrentComments);
    }
  }, [topicId]);

  const handleLogout = () => {
    navigate('/');
  };

  const handleSelectCourse = (courseId) => {
    navigate(`/home/${courseId}`);
  };
  
  const handleSelectTopic = (courseId, topicId) => {
    navigate(`/home/${courseId}/${topicId}`);
  };

  return (
    <div className="home">
      <Navbar courses={courses} onCourseSelect={handleSelectCourse} handleLogout={handleLogout}/>

      {!courseId && (
        <header className="introduction">
          Welcome to the forum. Please select one of your courses above to get started.
        </header>
      )}

      {courseId==0 && <CreateCourse />}

      {courses[courseId-1] && (
        <Sidebar
          topics={currentTopics}
          course={courses[courseId-1]}
          onTopicSelect={handleSelectTopic}
        />
      )}

      {topicId==0 && <CreateTopic />}
      {!topicId && courseId && <TopicIntro course={courses[courseId-1]} /> }
      {topics[topicId-1] && <Topic topic={topics[topicId-1]} />}
    </div>
  );
}

export default Home;

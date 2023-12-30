import { useState, useEffect } from "react";
import Navbar from "../layout/Navbar";
import Sidebar from "../layout/Sidebar";
import Post from "../layout/Post";
import Topic from "../layout/Topic";
import CreateCourse from "../layout/CreateCourse";
import CreatePost from "../layout/CreatePost";
import CourseIntro from "../layout/CourseIntro";
import "../../assets/styles/Home.css";
import {
  getCourses, getTopics, getPosts,
  createCourse, createTopic, createPost, createComment,
  updateCourse, updateTopic, updatePost, updateComment,
  deleteCourse, deleteTopic, deletePost, deleteComment,
} from "../../services/ApiService";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import { useUserContext } from "../../services/UserContext";

function Home() {
  let navigate = useNavigate();
  let { courseId, topicId, postId } = useParams();
  const [courses, setCourses] = useState([]);
  const [topics, setTopics] = useState([]);
  const [currentTopics, setCurrentTopics] = useState([]);
  const [posts, setPosts] = useState([]);
  const [userCourses, setUserCourses] = useState([]);
  const {user, logoutUser} = useUserContext();

  useEffect(() => {
    getCourses(user.id).then(setCourses);
  }, []);

  useEffect(() => {
    getTopics(user.id).then(setTopics);
  }, []);

  useEffect(() => {
    if (user) {
      const userCourseIds = user.courseRole.map(courseUserRole => courseUserRole.course_id);
      setUserCourses(courses.filter(course => userCourseIds.includes(course.id)));
    }
  }, [user, courses]);

  useEffect(() => {
    if (courseId) {
      setCurrentTopics(topics.filter(topic => topic.course_id === courseId));
    }
  }, [courseId]);

  useEffect(() => {
    if (topicId) {
      getPosts(topicId).then(setPosts);
    }
  }, [topicId]);

  const handleLogout = () => {
    logoutUser();
    navigate("/");
  };

  const handleSelectCourse = (courseId) => {
    navigate(`/home/${courseId}`);
  };

  const handleSelectTopic = (courseId, topicId) => {
    navigate(`/home/${courseId}/${topicId}`);
  };

  const handlePostClick = (postId) => {
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  };

  const handleCreateCourse = (id, name, desc) => {
    createCourse(user.id, id, name, desc);
    navigate(`/home/`);
  }

  const handleCreateTopic = (name) => {
    createTopic(user.id, courseId, name);
    navigate(`/home/${courseId}/`);
  }

  const handleCreatePost = (title, content) => {
    createPost(user.id, courseId, topicId, title, content);
    navigate(`/home/${courseId}/${topicId}/`);
  }

  const handleCreateComment = (content) => {
    createComment(user.id, courseId, topicId, postId, content);
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleUpdateCourse = (name, desc) => {
    updateCourse(user.id, courseId, name, desc);
    navigate(`/home/${courseId}/`);
  }

  const handleUpdateTopic = (topicId, name) => {
    updateTopic(user.id, courseId, topicId, name);
  }

  const handleUpdatePost = (title, content) => {
    updatePost(user.id, courseId, topicId, postId, title, content);
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleUpdateComment = (content) => {
    updateComment(user.id, courseId, topicId, postId, content);
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleDeleteCourse = () => {
    deleteCourse(user.id, courseId);
    navigate(`/home/`);
  }

  const handleDeleteTopic = () => {
    deleteTopic(user.id, courseId, topicId);
    navigate(`/home/${courseId}/`);
  }

  const handleDeletePost = () => {
    deletePost(user.id, courseId, topicId, postId);
    navigate(`/home/${courseId}/${topicId}/`);
  }

  const handleDeleteComment = () => {
    deleteComment(user.id, courseId, topicId, postId);
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  return (
    <div className="home">
      <Navbar
        roleId={user?.courseRole[0]?.role_id}
        courses={userCourses}
        onCourseSelect={handleSelectCourse}
        handleLogout={handleLogout}
      />

      {/*Forum intro*/}
      {!courseId && (
        <header className="introduction">
          Welcome to the forum. Please select one of your courses above to get
          started.
        </header>
      )}

      {/*Create course form*/}
      {courseId == 0 && <CreateCourse handleCreateCourse={handleCreateCourse}/>}

      {/*Sidebar showing all topics*/}
      {userCourses[courseId - 1] && (
        <Sidebar
          roleId={user?.courseRole[0]?.role_id}
          topics={currentTopics}
          course={userCourses[courseId - 1]}
          onTopicSelect={handleSelectTopic}
          handleCreateTopic={handleCreateTopic}
          handleUpdateTopic={handleUpdateTopic}
          handleDeleteTopic={handleDeleteTopic}
        />
      )}

      {/*Course intro/course edit*/}
      {!topicId && userCourses[courseId - 1] && 
        <CourseIntro 
          roleId={user?.courseRole[0]?.role_id}
          course={userCourses[courseId - 1]} 
          handleUpdateCourse={handleUpdateCourse}
          handleDeleteCourse={handleDeleteCourse}
        />
      }

      {/*Create topic form*/}
      {topicId == 0 && <CreateTopic handleCreateTopic={handleCreateTopic}/>}

      {/*Topic view showing all posts as cards*/}
      {topics[topicId - 1] && !postId && 
        <Topic 
          topic={topics[topicId - 1]}
          posts={posts}
          handlePostClick={handlePostClick}
        />
      }

      {/*Create post form*/}
      {postId==0 && <CreatePost handleCreatePost={handleCreatePost}/>}
        
      {/*Post view*/}
      {posts[postId - 1] && postId && 
        <Post 
          user={user}
          post={posts[postId - 1]} 
          handleUpdatePost={handleUpdatePost}
          handleDeletePost={handleDeletePost}
          handleCreateComment={handleCreateComment}
          handleUpdateComment={handleUpdateComment}
          handleDeleteComment={handleDeleteComment}
        />
      }
    </div>
  );
}

export default Home;

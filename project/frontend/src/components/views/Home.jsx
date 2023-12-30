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
  getCourses, getTopics, getPosts, getComments,
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
  const [posts, setPosts] = useState([]);
  const [comments, setComments] = useState([]);
  const [userCourses, setUserCourses] = useState([]);
  const [update, setUpdate] = useState(false)
  const {user, logoutUser} = useUserContext();
  const [updateCourses, setUpdateCourses] = useState(false)
  const [updateTopics, setUpdateTopics] = useState(false)
  const [updatePosts, setUpdatePosts] = useState(false)
  const [updateComments, setUpdateComments] = useState(false)

  useEffect(() => {
    getCourses(user.id).then(setCourses);
  }, [ updateCourses ]);

  useEffect(() => {
      getTopics(user.id).then(setTopics);
  }, [ updateTopics ])

  useEffect(() => {
      getPosts(user.id).then(setPosts);
  }, [ updatePosts ])

  useEffect(() => {
      getComments(user.id).then(setComments);
  }, [ updateComments ])

  useEffect(() => {
    if (user && courses) {
      const userCourseIds = user.courseRoles.map(courseUserRole => courseUserRole.courseId);
      setUserCourses(courses.filter(course => userCourseIds.includes(course.id)));
    }
  }, [user, courses]);

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
    setUpdateCourses(!updateCourses)
    navigate(`/home/`);
  }

  const handleCreateTopic = (name) => {
    createTopic(user.id, courseId, name);
    setUpdateTopics(!updateTopics)
    navigate(`/home/${courseId}/`);
  }

  const handleCreatePost = (title, content) => {
    createPost(user.id, courseId, topicId, title, content);
    setUpdatePosts(!updatePosts)
    navigate(`/home/${courseId}/${topicId}/`);
  }

  const handleCreateComment = (content) => {
    createComment(user.id, courseId, topicId, postId, content);
    setUpdateComments(!updateComments)
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleUpdateCourse = (name, desc) => {
    updateCourse(user.id, courseId, name, desc);
    setUpdateCourses(!updateCourses)
    navigate(`/home/${courseId}/`);
  }

  const handleUpdateTopic = (topicId, name) => {
    updateTopic(user.id, courseId, topicId, name);
    setUpdateTopics(!updateTopics)
  }

  const handleUpdatePost = (title, content) => {
    updatePost(user.id, courseId, topicId, postId, title, content);
    setUpdatePosts(!updatePosts)
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleUpdateComment = (content) => {
    updateComment(user.id, courseId, topicId, postId, content);
    setUpdateComments(!updateComments)
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleDeleteCourse = () => {
    deleteCourse(user.id, courseId);
    setUpdateCourses(!updateCourses)
    navigate(`/home/`);
  }

  const handleDeleteTopic = () => {
    deleteTopic(user.id, courseId, topicId);
    setUpdateTopics(!updateTopics)
    navigate(`/home/${courseId}/`);
  }

  const handleDeletePost = () => {
    deletePost(user.id, courseId, topicId, postId);
    setUpdatePosts(!updatePosts)
    navigate(`/home/${courseId}/${topicId}/`);
  }

  const handleDeleteComment = () => {
    deleteComment(user.id, courseId, topicId, postId);
    setUpdateComments(!updateComments)
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  return (
    <div className="home">
     <Navbar
        roleId={user.courseRoles[0]?.roleId}
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
      {courses.find(course => course.id == courseId) && (
        <Sidebar
          roleId={user.courseRoles[0]?.roleId}
          topics={topics.filter(topic => topic.courseId == courseId)}
          course={userCourses.find(course => course.id == courseId)}
          onTopicSelect={handleSelectTopic}
          handleCreateTopic={handleCreateTopic}
          handleUpdateTopic={handleUpdateTopic}
          handleDeleteTopic={handleDeleteTopic}
        />
      )}

      {/*Course intro/course edit*/}
      {!topicId && userCourses.find(course => course.id == courseId) && 
        <CourseIntro 
          roleId={user?.courseRoles[0]?.roleId}
          course={userCourses.find(course => course.id == courseId)} 
          handleUpdateCourse={handleUpdateCourse}
          handleDeleteCourse={handleDeleteCourse}
        />
      }

      {/*Create topic form*/}
      {topicId == 0 && <CreateTopic handleCreateTopic={handleCreateTopic}/>}

      {/*Topic view showing all posts as cards*/}
      {topics.find(topic => topic.id == topicId) && !postId && 
        <Topic 
          topic={topics.find(topic => topic.id == topicId)}
          posts={posts.filter(post => post.topicId == topicId)}
          handlePostClick={handlePostClick}
        />
      }

      {/*Create post form*/}
      {postId==0 && <CreatePost handleCreatePost={handleCreatePost}/>}
        
      {/*Post view*/}
      {posts.find(post => post.id == postId) && postId && 
        <Post 
          user={user}
          post={posts.find(post => post.id == postId)}
          comments={comments.filter(comment => comment.postId == postId)}
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

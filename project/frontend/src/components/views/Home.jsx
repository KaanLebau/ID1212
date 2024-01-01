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
  getCourses, getTopics, getPosts, getComments, getTopicList, 
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
  const {user, logoutUser} = useUserContext();

  const updateCourses = () => {
    getCourses(user.id).then(setCourses);
  }

  const updateTopics = () => {
    if(courses.find(course => course.id == courseId))
      getTopicList(user.id, courses.find(course => course.id == courseId).topicIdList);
  }

  const updatePosts = () => {
    if(topics.find(topic => topic.id == topicId)){
      getPosts(user.id, courseId, topicId).then(setPosts);
    }
  }

  const updateComments = () => {
    if(posts.find(post => post.id == postId))
      getComments(user.id).then(setComments);
    console.log(comments)
  }

  useEffect(() => {
    updateCourses();
  }, [ user ]);

  useEffect(() => {
    updateTopics();
  }, [ courseId ])

  useEffect(() => {
    updatePosts();
  }, [ topicId ])

  useEffect(() => {
    updateComments();
  }, [ postId ])

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
    createCourse(user.id, id, name, desc).then(updateCourses);
  }

  const handleCreateTopic = (name) => {
    createTopic(user.id, courseId, name).then(updateTopics);
  }

  const handleCreatePost = (title, content) => {
    createPost(user.id, courseId, topicId, title, content).then(updatePosts);
    navigate(`/home/${courseId}/${topicId}/`);
  }

  const handleCreateComment = (content) => {
    createComment(user.id, courseId, topicId, postId, content).then(updateComments);
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleUpdateCourse = (id, name, desc) => {
    updateCourse(user.id, courseId, id, name, desc).then(updateCourses);
    navigate(`/home/${courseId}/`);
  }

  const handleUpdateTopic = (topicId, name) => {
    updateTopic(user.id, courseId, topicId, name).then(updateTopics);
    navigate(`/home/${courseId}/`);
  }

  const handleUpdatePost = (title, content) => {
    updatePost(user.id, courseId, topicId, postId, title, content).then(updatePosts);
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleUpdateComment = (content) => {
    updateComment(user.id, courseId, topicId, postId, content).then(updateComments);
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  const handleDeleteCourse = () => {
    deleteCourse(user.id, courseId).then(updateCourses);
    navigate(`/home/`);
  }

  const handleDeleteTopic = (id) => {
    deleteTopic(user.id, courseId, id).then(updateTopics);
  }

  const handleDeletePost = () => {
    deletePost(user.id, courseId, topicId, postId).then(updatePosts);
    navigate(`/home/${courseId}/${topicId}/`);
  }

  const handleDeleteComment = () => {
    deleteComment(user.id, courseId, topicId, postId).then(updateComments);
    navigate(`/home/${courseId}/${topicId}/${postId}`);
  }

  return (
    <div className="home">
     <Navbar
        roleId={user.courseRoles[0]?.roleId}
        courses={courses}
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
          course={courses.find(course => course.id == courseId)}
          onTopicSelect={handleSelectTopic}
          handleCreateTopic={handleCreateTopic}
          handleUpdateTopic={handleUpdateTopic}
          handleDeleteTopic={handleDeleteTopic}
        />
      )}

      {/*Course intro/course edit*/}
      {!topicId && courses.find(course => course.id == courseId) && 
        <CourseIntro 
          roleId={user?.courseRoles[0]?.roleId}
          course={courses.find(course => course.id == courseId)} 
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

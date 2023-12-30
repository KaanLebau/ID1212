import axios from 'axios';

const API_URL = 'http://localhost:5000/api/v1'; // Your API endpoint

const apiCall = async (url) => {
  try {
    const response = await axios.get(API_URL + url);
    return response.data;
  } catch (error) {
    console.error('An error occurred while fetching the data:', error);
  }
}

export const getCourses = async (userId) => {
  return apiCall(`/user/${userId}/courses`);
}

export const getCourse = async (courseId) => {
  return apiCall(`/course/${courseId}`);
}

export const getUserCourses = async (user) => {
  return apiCall(`/user/${user.id}/courses`);
}

export const getTopics = async (userId) => {
  return apiCall(`/user/${userId}/topics`);
}

export const getPostsByTopic = async (topicId) => {
  return apiCall(`/posts/postbytopic/${topicId}`);
}

export const getTopicByName = async (topicName) => {
  return apiCall(`/topics/topicbyname/${topicName}`);
}

export const createCourse = async (userId, id, name, desc) => {
  try {
    const response = await axios.post(`${API_URL}/user/${userId}/course/new`, {
      courseId: id,
      courseName: name,
      courseDesc: desc
    });
  } catch (error) {
    console.error('An error occurred during course creation:', error.response || error);
    throw error;
  }
}

export const createTopic = async (userId, courseId, name) => {
  try {
    const response = await axios.post(`${API_URL}/user/${userId}/course/${courseId}/topic/new`, {
      name: name
    });
  } catch (error) {
    console.error('An error occurred during topic creation:', error.response || error);
    throw error;
  }
}

export const createPost = async (userId, courseId, topicId, title, content) => {
  try {
    const response = await axios.post(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}/post/new`, {
      title: title,
      content: content
    });
  } catch (error) {
    console.error('An error occurred during post creation:', error.response || error);
    throw error;
  }
}

export const createComment = async (userId, courseId, topicId, postId, comment) => {
  try {
    const response = await axios.post(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}/post/${postId}/comment/new`, {
      comment: comment
    });
  } catch (error) {
    console.error('An error occurred during comment creation:', error.response || error);
    throw error;
  }
}

export const updateCourse = async (userId, courseId, name, desc) => {
  try {
    const response = await axios.put(`${API_URL}/user/${userId}/course/update/${courseId}`, {
      courseName: name,
      courseDesc: desc
    });
  } catch (error) {
    console.error('An error occurred during course update:', error.response || error);
    throw error;
  }
}

export const updateTopic = async (userId, courseId, topicId, name) => {
  try {
    const response = await axios.put(`${API_URL}/user/${userId}/course/${courseId}/topic/update/${topicId}`, {
      name: name
    });
  } catch (error) {
    console.error('An error occurred during topic update:', error.response || error);
    throw error;
  }
}

export const updatePost = async (userId, courseId, topicId, postId, title, content) => {
  try {
    const response = await axios.put(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}/post/update/${postId}`, {
      title: title,
      content: content
    });
    console.log(response)
  } catch (error) {
    console.error('An error occurred during post update:', error.response || error);
    throw error;
  }
}

export const updateComment = async (userId, courseId, topicId, postId, commentId, content) => {
  try {
    const response = await axios.put(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}/post/${postId}/comment/update/${commentId}`, {
      content: content
    });
  } catch (error) {
    console.error('An error occurred during comment update:', error.response || error);
    throw error;
  }
}

export const deleteCourse = async (userId, courseId) => {
  try {
    const response = await axios.delete(`${API_URL}/user/${userId}/course/${courseId}`);
  } catch (error) {
    console.error('An error occurred during course deletion:', error.response || error);
    throw error;
  }
}

export const deleteTopic = async (userId, courseId, topicId) => {
  try {
    const response = await axios.delete(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}`);
  } catch (error) {
    console.error('An error occurred during topic deletion:', error.response || error);
    throw error;
  }
}

export const deletePost = async (userId, courseId, topicId, postId) => {
  try {
    const response = await axios.delete(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}/post/${postId}`);
  } catch (error) {
    console.error('An error occurred during post deletion:', error.response || error);
    throw error;
  }
}

export const deleteComment = async (userId, courseId, topicId, postId, commentId) => {
  try {
    const response = await axios.delete(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}/post/${postId}/comment/${commentId}`);
  } catch (error) {
    console.error('An error occurred during comment deletion:', error.response || error);
    throw error;
  }
}

export const setUserDisplayName = async (userId, displayName) => {
  try {
    const response = await axios.put(`${API_URL}/user/update/${userId}`, {
      content: content
    });
  } catch (error) {
    console.error('An error occurred during display name setting:', error.response || error);
    throw error;
  }
}

const mockData = {
  posts: [
    { 
      id: 1, 
      title: 'Introduction to Programming', 
      content: 'This post discusses the basics of programming languages like Python and JavaScript.', 
      created: '2023-01-10', 
      updated: '2023-01-15',
      user: {
        id: 1,
        username: 'john_doe',
        displayName: 'John Doe',
        role: 'STUDENT',
      },
      comments: [
        { id: 1, user: {displayName: 'Alice', role: 'STUDENT', id: 1}, created: '2023-01-10', updated: '2023-01-22', comment: 'Very helpful for beginners.' },
        { id: 2, user: {displayName: 'Bob', role: 'TEACHER', id: 2}, created: '2023-01-10', updated: '2023-01-22', comment: 'Looking forward to more advanced topics.' }
      ]
    },
    { 
      id: 2, 
      title: 'Front-end Development Trends', 
      content: 'Exploring the latest trends in front-end development, including frameworks and design patterns.', 
      created: '2023-02-20', 
      updated: '2023-02-22',
      user: {
        id: 1,
        username: 'john_doe',
        displayName: 'John Doe',
        role: 'STUDENT',
      },
      comments: [
        { id: 1, user: {displayName: 'Charlie', role: 'STUDENT', id: 1}, created: '2023-01-10', updated: '2023-01-22', comment: 'React has been a game changer.' },
        { id: 2, user: {displayName: 'David', role: 'STUDENT', id: 2}, created: '2023-01-10', updated: '2023-01-22', comment: 'Vue.js deserves more attention.' }
      ]
    },
    { 
      id: 3, 
      title: 'Machine Learning Basics', 
      content: 'A beginner-friendly introduction to machine learning concepts and applications.', 
      created: '2023-03-05', 
      updated: '2023-03-10',
      user: {
        id: 1,
        username: 'john_doe',
        displayName: 'John Doe',
        role: 'STUDENT',
      },
      comments: [
        { id: 1, user: {displayName: 'Eve', role: 'STUDENT', id: 2}, created: '2023-01-10', updated: '2023-01-22', comment: 'AI is fascinating!', },
        { id: 2, user: {displayName: 'Frank', role: 'STUDENT', id: 1}, created: '2023-01-10', updated: '2023-01-22', comment: 'Can you cover neural networks next?' }
      ]
    },
    { 
      id: 4, 
      title: 'Cybersecurity Essentials', 
      content: 'Discussing the importance of cybersecurity in today’s digital age and best practices to follow.', 
      created: '2023-04-15', 
      updated: '2023-04-18',
      user: {
        id: 1,
        username: 'john_doe',
        displayName: 'John Doe',
        role: 'STUDENT',
      },
      comments: [
        { id: 1, user: {displayName: 'Grace', role: 'STUDENT', id: 1}, created: '2023-01-10', updated: '2023-01-22', comment: 'Security is crucial for every developer.' },
        { id: 2, user: {displayName: 'Henry', role: 'STUDENT', id: 2}, created: '2023-01-10', updated: '2023-01-22', comment: 'More posts like this, please!' }
      ]
    }
  ],
  user: {
    id: 1,
    username: 'john_doe',
    displayName: 'John Doe',
    role: 'STUDENT',
    postList: [],
    commentList: [],
    courseRole: [{role_id: 1, course_id: 1}, {role_id: 2, course_id: 2}],
  },
    };

  export const getCommentsByTopic = async (topicId) => {
    return Promise.resolve(mockData.topics[topicId - 1].comments);
  }

  export const getUser = async (username, password) => {
    try {
      const response = await axios.post(`${API_URL}/login`, {
        username: username,
        password: password
      });
      return response.data;
    } catch (error) {
      console.error('An error occurred during login:', error.response || error);
      throw error;
    }
  };

  export const getUserFake = async (username, password) => {
    return Promise.resolve(mockData.user);
  }

  export const getPosts = async (topicId) => {
    return Promise.resolve(mockData.posts);
  }




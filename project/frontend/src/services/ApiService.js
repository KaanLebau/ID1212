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
    const response = await axios.delete(`${API_URL}/user/${userId}/course/delete/${courseId}`
    );
  } catch (error) {
    console.error('An error occurred during course deletion:', error.response || error);
    throw error;
  }
}

export const deleteTopic = async (userId, courseId, topicId) => {
  try {
    const response = await axios.delete(`${API_URL}/user/${userId}/course/${courseId}/topic/delete/${topicId}`);
  } catch (error) {
    console.error('An error occurred during topic deletion:', error.response || error);
    throw error;
  }
}

export const deletePost = async (userId, courseId, topicId, postId) => {
  try {
    const response = await axios.delete(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}/post/delete/${postId}`);
  } catch (error) {
    console.error('An error occurred during post deletion:', error.response || error);
    throw error;
  }
}

export const deleteComment = async (userId, courseId, topicId, postId, commentId) => {
  try {
    const response = await axios.delete(`${API_URL}/user/${userId}/course/${courseId}/topic/${topicId}/post/${postId}/comment/delete/${commentId}`);
  } catch (error) {
    console.error('An error occurred during comment deletion:', error.response || error);
    throw error;
  }
}

export const setUserDisplayName = async (userId, displayName) => {
  try {
    const response = await axios.put(`${API_URL}/user/update/${userId}`, {
      displayName: displayName
    });
  } catch (error) {
    console.error('An error occurred during display name setting:', error.response || error);
    throw error;
  }
}

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

  export const getPosts = async (userId) => {
    try{
      const response = await axios.get(`${API_URL}/user/${userId}/post/postList`);
      return response.data;
    } catch (error) {
      console.error('An error occurred during post retrieval:', error.response || error);
      throw error;
    }
  }

  export const getComments = async (userId) => {
    try{
      const response = await axios.get(`${API_URL}/user/${userId}/comment/commentList`);
      return response.data;
    } catch (error) {
      console.error('An error occurred during comment retrieval:', error.response || error);
      throw error;
    }
  }




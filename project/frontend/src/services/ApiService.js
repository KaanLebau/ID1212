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

export const getCourses = async () => {
  return apiCall("/courses/");
}

export const getCourse = async (courseId) => {
  return apiCall(`/course/${courseId}`);
}

export const getTopics = async () => {
  return apiCall("/topics/");
}

export const getTopicsByCourse = async (courseId) => {
  return apiCall(`/topics/topicbycourse/${courseId}`)
}

export const getPostsByTopic = async (topicId) => {
  return apiCall(`/posts/postbytopic/${topicId}`);
}

export const getTopicByName = async (topicName) => {
  return apiCall(`/topics/topicbyname/${topicName}`);
}

const mockData = {
  topics: [
      { subject: 'Topic 1', id: 1, content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', comments: [{ author: 'Alice', text: 'Great topic!' }, { author: 'Bob', text: 'Very informative.' }] },
      { subject: 'Topic 2', id: 2, content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', comments: [{ author: 'Charlie', text: 'Thanks!' }, { author: 'David', text: 'Awesome!' }] },
      { subject: 'Topic 3', id: 3, content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', comments: [{ author: 'Eve', text: 'Awesome!' }, { author: 'Frank', text: 'Awesome!' }] },
      { subject: 'Topic 4', id: 4, content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', comments: [{ author: 'Grace', text: 'Awesome!' }, { author: 'Henry', text: 'Awesome!' }] },],
  };

  export const getCommentsByTopic = async (topicId) => {
    return Promise.resolve(mockData.topics[topicId - 1].comments);
  }

  export const getUser = async (username, password) => {
    try {
      const response = await axios.post(`${API_URL}/login`, {
        username,
        password
      });
      return response.data;
    } catch (error) {
      console.error('An error occurred during login:', error.response || error);
      throw error;
    }
  };

  export const setUserDisplayName = (displayName) => {
    mockData.mockUser.displayName = displayName;
    return Promise.resolve(mockData.mockUser);
  };




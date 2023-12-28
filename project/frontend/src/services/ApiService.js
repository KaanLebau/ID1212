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
  posts: [
    { 
      id: 1, 
      title: 'Introduction to Programming', 
      content: 'This post discusses the basics of programming languages like Python and JavaScript.', 
      created: '2023-01-10', 
      updated: '2023-01-15',
      comments: [
        { author: 'Alice', text: 'Very helpful for beginners.' },
        { author: 'Bob', text: 'Looking forward to more advanced topics.' }
      ]
    },
    { 
      id: 2, 
      title: 'Front-end Development Trends', 
      content: 'Exploring the latest trends in front-end development, including frameworks and design patterns.', 
      created: '2023-02-20', 
      updated: '2023-02-22',
      comments: [
        { author: 'Charlie', text: 'React has been a game changer.' },
        { author: 'David', text: 'Vue.js deserves more attention.' }
      ]
    },
    { 
      id: 3, 
      title: 'Machine Learning Basics', 
      content: 'A beginner-friendly introduction to machine learning concepts and applications.', 
      created: '2023-03-05', 
      updated: '2023-03-10',
      comments: [
        { author: 'Eve', text: 'AI is fascinating!' },
        { author: 'Frank', text: 'Can you cover neural networks next?' }
      ]
    },
    { 
      id: 4, 
      title: 'Cybersecurity Essentials', 
      content: 'Discussing the importance of cybersecurity in today’s digital age and best practices to follow.', 
      created: '2023-04-15', 
      updated: '2023-04-18',
      comments: [
        { author: 'Grace', text: 'Security is crucial for every developer.' },
        { author: 'Henry', text: 'More posts like this, please!' }
      ]
    }
  ],
  user: {
    id: 1,
    username: 'john_doe',
    displayName: 'John Doe',
    role: 'STUDENT', // Simplified role representation
    postList: [], // Assuming this would be populated with post objects as needed
    commentList: [] // Assuming this would be populated with comment objects as needed
  },
    };

  export const getCommentsByTopic = async (topicId) => {
    return Promise.resolve(mockData.topics[topicId - 1].comments);
  }

  export const getUserReal = async (username, password) => {
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

  export const getUser = async (username, password) => {
    return Promise.resolve(mockData.user);
  }

  export const setUserDisplayName = (displayName) => {
    mockData.mockUser.displayName = displayName;
    return Promise.resolve(mockData.mockUser);
  };

  export const getPosts = async (topicId) => {
    return Promise.resolve(mockData.posts);
  }




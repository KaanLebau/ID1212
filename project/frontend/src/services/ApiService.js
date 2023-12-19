const mockData = {
    courses: [{ name: "ID1212", id: 1 }, { name: 'IV1351', id: 2 }, { name: 'IX1501', id: 3 }],
    topics: [
        { subject: 'Topic 1', id: 1, content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', comments: [{ author: 'Alice', text: 'Great topic!' }, { author: 'Bob', text: 'Very informative.' }] },
        { subject: 'Topic 2', id: 2, content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', comments: [{ author: 'Charlie', text: 'Thanks!' }, { author: 'David', text: 'Awesome!' }] },
        { subject: 'Topic 3', id: 3, content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', comments: [{ author: 'Eve', text: 'Awesome!' }, { author: 'Frank', text: 'Awesome!' }] },
        { subject: 'Topic 4', id: 4, content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', comments: [{ author: 'Grace', text: 'Awesome!' }, { author: 'Henry', text: 'Awesome!' }] },]
    };
  
  export const getCourses = () => {
    return Promise.resolve(mockData.courses);
  };
  
  export const getTopics = () => {
    //const topics = mockData.topics.filter(topic => topic.course_id === courseId);
    return Promise.resolve(mockData.topics);
  };

  export const getCourse = (courseId) => {
    return Promise.resolve(mockData.courses[courseId]);
  }

  export const getTopic = (topicId) => {
    return Promise.resolve(mockData.topics[topicId-1]);
  }
  
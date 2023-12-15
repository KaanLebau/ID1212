function updateCourseHeader(course) {
    const courseHeader = document.getElementById('course-header');
    courseHeader.innerHTML = '';
    const courseTitle = document.createElement('h2');
    courseTitle.textContent = course.name;
    courseHeader.appendChild(courseTitle);
}

function updateSidebar(topics, course) {
    const topicsContainer = document.createElement('div');
    topicsContainer.className = 'topics-container';
    topics.forEach(topic => {
        const topicItem = document.createElement('a');
        topicItem.href = `#c${course.id}t${topic.id}`;
        topicItem.textContent = topic.subject;
        topicItem.className = 'topic-item';
        topicsContainer.appendChild(topicItem);
    });
    const sidebar = document.querySelector('.sidebar');
    sidebar.appendChild(topicsContainer);
}

//TODO: Ladda från databasen
const course = {name: 'Course 1', id: 1}
const topics = [{subject:'Topic 1', id: 1}, {subject: 'Topic 2', id: 2}, {subject: 'Topic 3', id: 3}, {subject: 'Topic 4', id: 4}]

updateCourseHeader(course);
updateSidebar(topics, course);
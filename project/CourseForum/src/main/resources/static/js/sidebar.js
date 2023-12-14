function updateCourseHeader(courseName) {
    const courseHeader = document.getElementById('course-header');
    courseHeader.innerHTML = '';
    const courseTitle = document.createElement('h2');
    courseTitle.textContent = courseName;
    courseHeader.appendChild(courseTitle);
}

function updateSidebar(topics) {
    const topicsContainer = document.createElement('div');
    topicsContainer.className = 'topics-container';
    topics.forEach(topic => {
        const topicItem = document.createElement('a');
        topicItem.href = `#${topic.toLowerCase().replace(/\s+/g, '-')}`;
        topicItem.textContent = topic;
        topicItem.className = 'topic-item';
        topicsContainer.appendChild(topicItem);
    });
    const sidebar = document.querySelector('.sidebar');
    sidebar.appendChild(topicsContainer);
}

//Testdata, hämtas från databasen
updateCourseHeader('Course 1');
updateSidebar(['Topic 1', 'Topic 2', 'Topic 3', 'Topic 4']);
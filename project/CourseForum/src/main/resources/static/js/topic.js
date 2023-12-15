function renderTopicSection(topic) {
    const mainContent = document.querySelector('.main-content');
    mainContent.innerHTML = '';

    const topicHeader = document.createElement('section');
    topicHeader.id = 'topic-header';
    topicHeader.innerHTML = `<h2>${topic.name}</h2>`;

    const topicContent = document.createElement('section');
    topicContent.id = 'topic-content';
    topicContent.innerHTML = `<p>${topic.content}</p>`;

    const topicComments = document.createElement('section');
    topicComments.id = 'topic-comments';
    let commentsHTML = topic.comments.map(comment => {
        return `<div class="comment">
        <span class="comment-author">${comment.author}</span>
        <span class="comment-text">${comment.text}</span>
        </div>`;}).join('');
    topicComments.innerHTML = commentsHTML;

    mainContent.appendChild(topicHeader);
    mainContent.appendChild(topicContent);
    mainContent.appendChild(topicComments);
}

function loadTopic(topicId) {
    //TODO: Ladda från databasen
    const topicData = {
        name: `Topic ${topicId}`,
        content: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        comments: [
            { author: "Alice", text: "Great topic!" },
            { author: "Bob", text: "Very informative." }
        ]
    };

    renderTopicSection(topicData);
}

//Test
loadTopic(1);
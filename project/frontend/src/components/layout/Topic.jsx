import '../../assets/styles/Topic.css';

function Topic({ topic }) {
  return (
    <main className="main-content">
      <section id="topic-header">
        <h2>{topic.name}</h2>
      </section>
      <section id="topic-content">
        <p>{topic.content}</p>
      </section>
      <section id="topic-comments">
        {topic.comments.map((comment, index) => (
          <div key={index} className="comment">
            <span className="comment-author">{comment.author}</span>
            <span className="comment-text">{comment.text}</span>
          </div>
        ))}
      </section>
    </main>
  );
}

export default Topic;

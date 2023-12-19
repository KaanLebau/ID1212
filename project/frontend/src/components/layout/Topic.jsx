import PropTypes from 'prop-types';
import '../../assets/styles/Topic.css';

function Topic({topic}) {
    return (
        <main className="main-content">
            <section id="topic-header">
                <h2>{topic.subject}</h2>
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

Topic.propTypes = {
    topic: PropTypes.shape({
      subject: PropTypes.string.isRequired,
      content: PropTypes.string.isRequired,
      comments: PropTypes.arrayOf(PropTypes.shape({
        author: PropTypes.string.isRequired,
        text: PropTypes.string.isRequired
      })).isRequired
    }).isRequired
  };
export default Topic;

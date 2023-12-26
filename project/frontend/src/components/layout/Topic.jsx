import PropTypes from 'prop-types';
import '../../assets/styles/Topic.css';

function Topic({topic}) {
    return (
        <main className="main-content">
            <section id="topic-header">
                <h2>{topic.topicName}</h2>
            </section>
            <section id="topic-content">
                <p>{topic.topicName}</p>
            </section>
            <section id="topic-comments">
                {topic.postList.map((comment, index) => (
                    <div key={index} className="comment">
                        <span className="comment-author">{comment.author}</span>
                        <span className="comment-text">{comment.text}</span>
                    </div>
                ))}
            </section>
            <form method="post" action="/" id="comment-form">
                <textarea
                    type="text"
                    id="comment-form-box"
                    name="comment"
                    rows="4"
                    cols="50"
                />
                <p></p>
                <button type="submit" id="comment-form-button">Submit</button>
            </form>
        </main>
    );
}

Topic.propTypes = {
    topic: PropTypes.shape({
      topicName: PropTypes.string.isRequired,
      //postList: PropTypes.arrayOf(PropTypes.shape({
        //author: PropTypes.string.isRequired,
        //text: PropTypes.string.isRequired
      //})).isRequired
    }).isRequired
  };

export default Topic;

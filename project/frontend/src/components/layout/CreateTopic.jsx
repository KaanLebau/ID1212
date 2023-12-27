import '../../assets/styles/CreateTopic.css';

function CreateTopic(createTopic) {
    return (
      <div className="create-topic-view">
          <div className="create-topic-container">
            <div className="create-topic-message">
              <p>Create Topic:</p>
            </div>
            <form onSubmit={createTopic}>
              <div className="row">
                <label htmlFor="topicName">Topic Name: </label>
                <input
                  type="text"
                  placeholder="Topic Name"
                  id="topicName"
                  name="topicName"
                  className='create-topic-input'
                  required
                />
              </div>
              <div className="row">
                <label htmlFor="content">Content: </label>
                <textarea
                  type="text"
                  placeholder="Content"
                  id="content"
                  name="content"
                  rows="4"
                  className='create-topic-textarea'
                  required
                />
              </div>
              <div className="row">
                <button type="submit">Submit</button>
              </div>
            </form>
          </div>
        </div>
    );
}

export default CreateTopic;
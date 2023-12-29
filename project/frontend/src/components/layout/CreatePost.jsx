import '../../assets/styles/CreatePost.css';

function CreatePost(createPost) {
    return (
      <div className="create-post-view">
          <div className="create-post-container">
            <div className="create-post-message">
              <p>Create Post:</p>
            </div>
            <form onSubmit={createPost}>
              <div className="row">
                <label htmlFor="title">Title: </label>
                <input
                  type="text"
                  placeholder="Title"
                  id="title"
                  name="title"
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
                  className='create-post-textarea'
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

export default CreatePost;
import "../../assets/styles/CreatePost.css";

function CreatePost({ handleCreatePost }) {
  
  const handleSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const title = formData.get("title");
    const content = formData.get("content");

    handleCreatePost(title, content);
  };

  return (
    <div className="create-post-view">
      <div className="create-post-container">
        <div className="create-post-message">
          <p>Create Post</p>
        </div>
        <form onSubmit={handleSubmit}>
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
              className="create-post-textarea"
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

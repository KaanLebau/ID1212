import '../../assets/styles/CreateCourse.css';

function CreateCourse(createCourse) {
    return (
      <div className="create-course-view">
          <div className="create-course-container">
            <div className="create-course-message">
              <p>Create Course:</p>
            </div>
            <form onSubmit={createCourse}>
              <div className="row">
                <label htmlFor="courseId">Course ID: </label>
                <input
                  type="text"
                  placeholder="Course ID"
                  id="courseId"
                  name="courseId"
                  required
                />
              </div>
              <div className="row">
                <label htmlFor="courseName">Course Name: </label>
                <input
                  type="text"
                  placeholder="Course Name"
                  id="courseName"
                  name="courseName"
                  className='create-course-input'
                  required
                />
              </div>
              <div className="row">
                <label htmlFor="description">Description: </label>
                <textarea
                  placeholder="Description"
                  id="description"
                  name="description"
                  rows="4"
                  className='create-course-textarea'
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

export default CreateCourse;
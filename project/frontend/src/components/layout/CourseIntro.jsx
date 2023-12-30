import React, { useState } from 'react';
import '../../assets/styles/CourseIntro.css';

function CourseIntro({roleId, course, handleUpdateCourse, handleDeleteCourse}) {
    const [isEditing, setIsEditing] = useState(false);
    const [editedCourseId, setEditedCourseId] = useState(course.courseId);
    const [editedCourseName, setEditedCourseName] = useState(course.courseName);
    const [editedCourseDesc, setEditedCourseDesc] = useState(course.courseDesc);

    const handleEditToggle = () => {
        setIsEditing(!isEditing);
    };

    const handleSave = () => {
        handleUpdateCourse(editedCourseId, editedCourseName, editedCourseDesc);
        setIsEditing(false);
    };

    if (!course) {
        return <div>Loading course...</div>;
      }
      
      return (
        <div className="course-intro">
            {isEditing ? (
                <>
                <header className='course-intro-edit'>Edit Course</header>
                <header className="course-intro-logo">
                    <input 
                        type="text" 
                        value={editedCourseId} 
                        onChange={(e) => setEditedCourseId(e.target.value)} 
                        className="course-intro-id-input"
                    /> - 
                    <input 
                        type="text" 
                        value={editedCourseName} 
                        onChange={(e) => setEditedCourseName(e.target.value)} 
                        className="course-intro-name-input"
                    />
                    </header>
                    <textarea 
                        value={editedCourseDesc} 
                        onChange={(e) => setEditedCourseDesc(e.target.value)} 
                        className="course-intro-desc-textarea"
                    />
                    <div>
                        <button onClick={handleSave} className='course-intro-save-btn'>Save</button>
                        <button onClick={handleEditToggle} className='course-intro-abort-btn'>Abort</button>
                    </div>
                </>
            ) : (
                <>
                    <header className="course-intro-logo">{course.courseId + " - " + course.courseName}</header>
                    <p className="course-intro-description">{course.courseDesc}</p>
                    <p className='course-intro-message'>Please select a topic to get started.</p>
                    <div>
                        {roleId === 1 && <button onClick={handleEditToggle} className='course-intro-edit-btn'>Edit Course</button>}
                        {roleId === 1 && <button onClick={handleDeleteCourse} className='course-intro-delete-btn'>Delete Course</button>}
                    </div>
                </>
            )}
        </div>
    );
}

export default CourseIntro;
import PropTypes from 'prop-types';
import '../../assets/styles/Navbar.css';
import { useNavigate } from 'react-router-dom';

function Navbar({roleId, courses, onCourseSelect, handleLogout}) {
    let navigate = useNavigate();
    const changeDisplayname = () => {
        navigate("/displayname");
    }

    return (
        <header className="header">
        <div className="logo-nav-container">
            <div className="nav-logo">
                KTH-Forum
            </div>
            <nav id="navbar">
                {courses.map((course) => (
                    <a key={course.id} onClick={() => onCourseSelect(course.id)} className="navbar-link">
                        {course.courseId + " - " + course.courseName}
                    </a>
                ))}
                <div className='navbar-management'>
                {roleId === 1 && <a key={"create-course"} onClick={() => onCourseSelect(0)} className="create-course">Create New Course</a>}
                <a key={"change-displayname"} onClick={() => changeDisplayname()} className="change-displayname">Change Displayname</a>
                <a key={"logout"} onClick={() => handleLogout()} className="logout">Logout</a>
                </div>
            </nav>
        </div>
        </header>
    );
}

Navbar.propTypes = {
    courses: PropTypes.arrayOf(PropTypes.shape({
      id: PropTypes.number.isRequired,
      courseName: PropTypes.string.isRequired
    })).isRequired,
    onCourseSelect: PropTypes.func,
  };

export default Navbar;
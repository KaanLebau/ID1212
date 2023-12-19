import PropTypes from 'prop-types';
import '../../assets/styles/Navbar.css';

function Navbar({courses, onCourseSelect}) {
    return (
        <header className="header">
        <div className="logo-nav-container">
            <div className="nav-logo">
                KTH-Forum
            </div>
            <nav id="navbar">
                {courses.map((course) => (
                    <a key={course.id} onClick={() => onCourseSelect(course)} className="navbar-link">
                        {course.name}
                    </a>
                ))}
            </nav>
        </div>
        </header>
    );
}

Navbar.propTypes = {
    courses: PropTypes.arrayOf(PropTypes.shape({
      id: PropTypes.number.isRequired,
      name: PropTypes.string.isRequired
    })).isRequired,
    onCourseSelect: PropTypes.func,
  };

export default Navbar;
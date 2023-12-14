document.addEventListener('DOMContentLoaded', function() {
    const navbar = document.getElementById('navbar');
    const courses = ['Course 1', 'Course 2', 'Course 3']; //Testdata, hämtas från databasen

    courses.forEach(course => {
        const link = document.createElement('a');
        link.href = `/${course.toLowerCase().replace(/\s+/g, '')}`;
        link.textContent = course;
        link.className = 'navbar-link';
        navbar.appendChild(link);
    });
});
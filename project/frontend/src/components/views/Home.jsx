import { useState, useEffect } from 'react';
import Navbar from '../layout/Navbar';
import Sidebar from '../layout/Sidebar';
import Topic from '../layout/Topic';
import '../../assets/styles/Home.css';

function Home() {
    const courses = [{ name: "Course 1", id: 1 }, { name: 'Course 2', id: 2 }, { name: 'Course 3', id: 3 }];
    const course = { name: 'Course 1', id: 1 };
    const topics = [
        { subject: 'Topic 1', id: 1 },
        { subject: 'Topic 2', id: 2 },
        { subject: 'Topic 3', id: 3 },
        { subject: 'Topic 4', id: 4 },
    ];
    const [currentTopic, setCurrentTopic] = useState(null);

    useEffect(() => {
        // Here you would fetch data for the current topic from the backend when the Home component mounts or when a certain condition changes
        // For now, we'll simulate this with a static assignment
        const topicData = {
            name: 'Topic 1',
            content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
            comments: [
                { author: 'Alice', text: 'Great topic!' },
                { author: 'Bob', text: 'Very informative.' },
            ],
        };
        setCurrentTopic(topicData);
    }, []);

    return (
        <div className="home">
            <Navbar courses={courses} />
            <Sidebar topics={topics} course={course} />
            {currentTopic && <Topic topic={currentTopic} />}
        </div>
    );
}

export default Home;
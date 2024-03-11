
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTimes } from '@fortawesome/free-solid-svg-icons';

const Badge = ({ title, onClick, edit }) => {
    return (
        <div className="badge" onClick={onClick}>
            <span>{title}</span>
            {
                edit && (

                    <FontAwesomeIcon icon={faTimes} style={{ marginLeft: '5px' }} />
                )
            }
        </div>
    );
}

export default Badge;

import { createBoard } from '@wixc3/react-board';
import { FacultyPage } from '../../../components/faculty-page/faculty-page';

export default createBoard({
    name: 'FacultyPage',
    Board: () => <FacultyPage />
});

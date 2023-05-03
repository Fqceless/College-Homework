import { createBoard } from '@wixc3/react-board';
import App from '../../../App';

export default createBoard({
    name: 'App',
    Board: () => <App />,
    environmentProps: {
        canvasHeight: 1111,
        canvasWidth: 1922,
        windowWidth: 1920,
        windowHeight: 1080,
    },
});

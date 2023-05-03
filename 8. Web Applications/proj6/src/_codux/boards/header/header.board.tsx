import { createBoard } from '@wixc3/react-board';
import { Header } from '../../../components/header/header';

export default createBoard({
    name: 'Header',
    Board: () => <Header />,
    environmentProps: {
        canvasWidth: 1918,
        canvasHeight: 679,
        windowHeight: 1080,
        windowWidth: 1920,
    },
});

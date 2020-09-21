export function getSkinName() {
    const bodyElement = document.getElementsByTagName("body")[0];
    return bodyElement.classList[0];
}
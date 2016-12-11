;; a 3d cube that follows the mouse pointer in its orientation

(ns quil-3d-tutorial.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

  (def screen-width 1200)
  (def screen-height 720)

  (def xmag 0.0)
  (def ymag 0.0)

  (def pi 3.14159)

  (def vertices-for-colored-cube
    [[[144 255 33] [-1 1 1]]
    [[144 255 33] [1 1 1]]
    [[144 255 33] [1 -1 1]]
    [[144 255 33] [-1 -1 1]]
    [[43 255 133] [1 1 1]]
    [[43 255 133] [1 1 -1]]
    [[43 255 133] [1 -1 -1]]
    [[43 255 133] [1 -1 1]]
    [[177 255 43] [1 1 -1]]
    [[177 255 43] [-1 1 -1]]
    [[177 255 43] [-1 -1 -1]]
    [[177 255 43] [1 -1 -1]]
    [[219 73 155] [-1 1 -1]]
    [[219 73 155] [-1 1 1]]
    [[219 73 155] [-1 -1 1]]
    [[219 73 155] [-1 -1 -1]]
    [[91 22 255] [-1 1 -1]]
    [[91 22 255] [1 1 -1]]
    [[91 22 255] [1 1 1]]
    [[91 22 255] [-1 1 1]]
    [[189 104 255] [-1 -1 -1]]
    [[189 104 255] [1 -1 -1]]
    [[189 104 255] [1 -1 1]]
    [[189 104 255] [-1 -1 1]]])

  (defn draw [state]
    ;; erase everything that was drawn previously with a black background
    (q/background 0)

    (q/no-stroke)

    #_(q/push-matrix)
    (q/translate (/ screen-width 2) (/ screen-height 2) -10)

    #_(let [new-xmag (* (/ (q/mouse-x) screen-width) pi)
          new-ymag (* (/ (q/mouse-y) screen-height) pi)
          xmag-diff (- xmag new-xmag)
          ymag-diff (- ymag new-ymag)]
      (if (> (Math/abs xmag-diff) 0.01)
        (q/rotate-y (- xmag (/ xmag-diff 2.0)))
        (q/rotate-y (- xmag)))
      (if (> (Math/abs ymag-diff) 0.01)
        (q/rotate-x (- ymag (/ ymag-diff 2.0)))
        (q/rotate-x (- ymag))))

    ;; scale the image, so that we can see something
    (q/scale 100)

    ;; rotate the cube a little bit so that we can see three of its sides
    (q/rotate-x 0)
    (q/rotate-y 45)
    (q/rotate-z 45)

    (q/begin-shape :quads)
    (doseq [[c v] vertices-for-colored-cube]
      (apply q/fill c)
      (apply q/vertex v))
    (q/end-shape)
    #_(q/pop-matrix))

;; this is the place to set up the state
;; we are just setting the color mode here and returning an emtpy map because we are not using the state
(defn setup []
  (q/color-mode :rgb)
  {})

(defn -main [& args]
  (q/defsketch quil-experiments
    :title "You spin my cube right round"
    :size [screen-width screen-height]
    ; setup function called only once, during sketch initialization.
    :setup setup
    :draw draw
    ; use functional-mode middleware.
    :middleware [m/fun-mode]
    :renderer :opengl))
